package nl.tue.win.vcp.virtualbreitenbergenvironment.utility;

import java.io.*;
import java.nio.*;
import java.util.*;
import java.util.zip.*;
import javax.media.opengl.*;
import com.jogamp.opengl.util.*;
import static javax.media.opengl.GL2.*;

/**
 * Wavefront .obj mesh loader with vertices, face and normal support. Provides a
 * convenience method to load the whole model as display-list. The code is
 * slightly modified copypasta from the open source project "jglmark"
 * (https://jglmark.dev.java.net/). Original author is Chris "Crash0veride007"
 * Brown (crash0veride007@gmail.com). Also added support for compressed mesh
 * files (.zip).
 *
 * Modified slightly by Maikel Steneker to integrate in Visual Computing
 * Project.
 *
 */
public class WavefrontObjectLoader_DisplayList {

    public interface Log {

        public void info(String msg);

        public void fatalerror(String msg);

        public void fatalerror(Exception e);
    }

    public class StandardLog implements Log {

        @Override
        public void info(String msg) {
            System.out.println("INFO: " + msg);
        }

        @Override
        public void fatalerror(String msg) {
            System.err.println("ERROR: " + msg);
        }

        @Override
        public void fatalerror(Exception e) {
            fatalerror(e.toString());
        }
    }

    public class DummyLog implements Log {

        @Override
        public void info(String msg) {
        }

        @Override
        public void fatalerror(String msg) {
        }

        @Override
        public void fatalerror(Exception e) {
        }

    }

    public class MaterialTuple {

        public int startIndex; // first face that uses the material
        public String material; // name of the material

        public MaterialTuple(int index, String line) {
            this.startIndex = index;
            String[] split = line.split(" ");
            if (split.length > 1) {
                this.material = split[1];
            } else {
                this.material = line;
            }
        }

        @Override
        public String toString() {
            return "MaterialTuple{" + "startIndex=" + startIndex + ", material=" + material + '}';
        }

    }

    private final List<MaterialTuple> materials = new ArrayList<>();

    private final static boolean ENABLE_LOGGING = false;
    private final Log log = ENABLE_LOGGING ? new StandardLog() : new DummyLog();

    private final String OBJModelPath;                                    //the path to the model file
    private final List<float[]> vData = new ArrayList<>();    //list of vertex coordinates
    private final List<float[]> vtData = new ArrayList<>();   //list of texture coordinates
    private final List<float[]> vnData = new ArrayList<>();   //list of normal coordinates
    private final List<int[]> fv = new ArrayList<>();           //face vertex indices
    private final List<int[]> ft = new ArrayList<>();           //face texture indices
    private final List<int[]> fn = new ArrayList<>();           //face normal indices
    private FloatBuffer modeldata;                                  //buffer which will contain vertice data
    private int FaceFormat;                                         //format of the faces triangles or quads
    private int FaceMultiplier;                                     //number of possible coordinates per face
    private int PolyCount = 0;                                      //the model polygon count
    private boolean init = true;

    public WavefrontObjectLoader_DisplayList(String inModelPath) {
        log.info("LOADING WAVEFRONT OBJECT MODEL " + inModelPath);
        OBJModelPath = inModelPath;
        LoadOBJModel(OBJModelPath);
        SetFaceRenderType();
        log.info("POLYGON COUNT FOR MODEL=" + PolyCount);
        log.info("VERTEX COUNT FOR MODEL=" + vData.size());
        log.info("TEXTURE COORDINATE COUNT FOR MODEL=" + vtData.size());
        log.info("NORMAL COUNT FOR MODEL=" + vnData.size());
    }

    private void LoadOBJModel(String ModelPath) {
        try {
            BufferedReader br = null;
            if (ModelPath.endsWith(".zip")) {
                log.info("WAVEFRONT MESH IS COMPRESSED! TRY TO EXTRACT FIRST/SINGLE ENTRY!");
                ZipInputStream tZipInputStream = new ZipInputStream(new BufferedInputStream((new Object()).getClass().getResourceAsStream(ModelPath)));
                ZipEntry tZipEntry;
                tZipEntry = tZipInputStream.getNextEntry();
                String inZipEntryName = tZipEntry.getName();
                if (inZipEntryName == null) {
                    log.fatalerror("ERROR! ZIP ENTRY IS NULL!");
                }
                log.info("EXTRACTING: " + inZipEntryName);
                if (!tZipEntry.isDirectory()) {
                    br = new BufferedReader(new InputStreamReader(tZipInputStream));
                } else {
                    log.fatalerror("ERROR! ZIP ENTRY IS DIRECTORY! SHOULD BE PLAIN FILE!");
                }
            } else {
                //br = new BufferedReader(new InputStreamReader((new Object()).getClass().getResourceAsStream(ModelPath)));
                br = new BufferedReader(new FileReader(ModelPath));
            }
            LoadOBJModel(br);
            log.info("MODEL " + ModelPath + " SUCCESSFULLY LOADED!");
        } catch (IOException e) {
            log.fatalerror(e);
        }
    }

    private void LoadOBJModel(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            if (line.startsWith("#")) {         //read any descriptor data in the file
                // Zzzz ...
            } else if (line.equals("")) {
                // Ignore whitespace data
            } else if (line.startsWith("v ")) {  //read in vertex data
                vData.add(ProcessData(line));
            } else if (line.startsWith("vt ")) { //read texture coordinates
                vtData.add(ProcessData(line));
            } else if (line.startsWith("vn ")) { //read normal coordinates
                vnData.add(ProcessData(line));
            } else if (line.startsWith("f ")) {  //read face data
                ProcessfData(line);
            } else if (line.startsWith("usemtl ")) {
                materials.add(new MaterialTuple(fv.size(), line));
            }
        }
        br.close();
    }

    private float[] ProcessData(String read) {
        final String s[] = read.split("\\s+");
        return (ProcessFloatData(s)); //returns an array of processed float data
    }

    private float[] ProcessFloatData(String sdata[]) {
        float data[] = new float[sdata.length - 1];
        for (int loop = 0; loop < data.length; loop++) {
            data[loop] = Float.parseFloat(sdata[loop + 1]);
        }
        return data; //return an array of floats
    }

    private void ProcessfData(String fread) {
        PolyCount++;
        String s[] = fread.split("\\s+");
        if (fread.contains("//")) { //pattern is present if obj has only v and vn in face data
            for (int loop = 1; loop < s.length; loop++) {
                s[loop] = s[loop].replaceAll("//", "/0/"); //insert a zero for missing vt data
            }
        }
        ProcessfIntData(s); //pass in face data
    }

    private void ProcessfIntData(String sdata[]) {
        int vdata[] = new int[sdata.length - 1];
        int vtdata[] = new int[sdata.length - 1];
        int vndata[] = new int[sdata.length - 1];
        for (int loop = 1; loop < sdata.length; loop++) {
            String s = sdata[loop];
            String[] temp = s.split("/");
            vdata[loop - 1] = Integer.valueOf(temp[0]);         //always add vertex indices
            if (temp.length > 1) {                              //we have v and vt data
                vtdata[loop - 1] = Integer.valueOf(temp[1]);    //add in vt indices
            } else {
                vtdata[loop - 1] = 0;                           //if no vt data is present fill in zeros
            }
            if (temp.length > 2) {                              //we have v, vt, and vn data
                vndata[loop - 1] = Integer.valueOf(temp[2]);    //add in vn indices
            } else {
                vndata[loop - 1] = 0;                           //if no vn data is present fill in zeros
            }
        }
        fv.add(vdata);
        ft.add(vtdata);
        fn.add(vndata);
    }

    private void SetFaceRenderType() {
        final int temp[] = (int[]) fv.get(0);
        if (temp.length == 3) {
            FaceFormat = GL_TRIANGLES; 	//the faces come in sets of 3 so we have triangular faces
            FaceMultiplier = 3;
        } else if (temp.length == 4) {
            FaceFormat = GL_QUADS; 		//the faces come in sets of 4 so we have quadrilateral faces
            FaceMultiplier = 4;
        } else {
            FaceFormat = GL_POLYGON; 	//fall back to render as free form polygons
        }
    }

    private void ConstructInterleavedArray(GL2 inGL) {
        final int tv[] = (int[]) fv.get(0);
        final int tt[] = (int[]) ft.get(0);
        final int tn[] = (int[]) fn.get(0);
        //if a value of zero is found that it tells us we don't have that type of data
        if ((tv[0] != 0) && (tt[0] != 0) && (tn[0] != 0)) {
            ConstructTNV(); //we have vertex, 2D texture, and normal Data
            inGL.glInterleavedArrays(GL_T2F_N3F_V3F, 0, modeldata);
        } else if ((tv[0] != 0) && (tt[0] != 0) && (tn[0] == 0)) {
            ConstructTV(); //we have just vertex and 2D texture Data
            inGL.glInterleavedArrays(GL_T2F_V3F, 0, modeldata);
        } else if ((tv[0] != 0) && (tt[0] == 0) && (tn[0] != 0)) {
            ConstructNV(); //we have just vertex and normal Data
            inGL.glInterleavedArrays(GL_N3F_V3F, 0, modeldata);
        } else if ((tv[0] != 0) && (tt[0] == 0) && (tn[0] == 0)) {
            ConstructV();
            inGL.glInterleavedArrays(GL_V3F, 0, modeldata);
        }
    }

    private void ConstructTNV() {
        int[] v, t, n;
        float tcoords[] = new float[2]; //only T2F is supported in interLeavedArrays!!
        float coords[] = new float[3];
        int fbSize = PolyCount * (FaceMultiplier * 8); //3v per poly, 2vt per poly, 3vn per poly
        modeldata = GLBuffers.newDirectFloatBuffer(fbSize);
        modeldata.position(0);
        for (int oloop = 0; oloop < fv.size(); oloop++) {
            v = (int[]) (fv.get(oloop));
            t = (int[]) (ft.get(oloop));
            n = (int[]) (fn.get(oloop));
            for (int iloop = 0; iloop < v.length; iloop++) {
                //fill in the texture coordinate data
                System.arraycopy((float[]) vtData.get(t[iloop] - 1), 0, tcoords, 0, tcoords.length); //only T2F is supported in interleavedarrays!!
                modeldata.put(tcoords);
                //fill in the normal coordinate data
                System.arraycopy((float[]) vnData.get(n[iloop] - 1), 0, coords, 0, coords.length);
                modeldata.put(coords);
                //fill in the vertex coordinate data
                System.arraycopy((float[]) vData.get(v[iloop] - 1), 0, coords, 0, coords.length);
                modeldata.put(coords);
            }
        }
        modeldata.position(0);
    }

    private void ConstructTV() {
        int[] v, t;
        float tcoords[] = new float[2]; //only T2F is supported in interLeavedArrays!!
        float coords[] = new float[3];
        int fbSize = PolyCount * (FaceMultiplier * 5); //3v per poly, 2vt per poly
        modeldata = GLBuffers.newDirectFloatBuffer(fbSize);
        modeldata.position(0);
        for (int oloop = 0; oloop < fv.size(); oloop++) {
            v = (int[]) (fv.get(oloop));
            t = (int[]) (ft.get(oloop));
            for (int iloop = 0; iloop < v.length; iloop++) {
                //fill in the texture coordinate data
                System.arraycopy((float[]) vtData.get(t[iloop] - 1), 0, tcoords, 0, tcoords.length); //only T2F is supported in interleavedarrays!!
                modeldata.put(tcoords);
                //fill in the vertex coordinate data
                System.arraycopy((float[]) vData.get(v[iloop] - 1), 0, coords, 0, coords.length);
                modeldata.put(coords);
            }
        }
        modeldata.position(0);
    }

    private void ConstructNV() {
        int[] v, n;
        float coords[] = new float[3];
        int fbSize = PolyCount * (FaceMultiplier * 6); //3v per poly, 3vn per poly
        modeldata = GLBuffers.newDirectFloatBuffer(fbSize);
        modeldata.position(0);
        for (int oloop = 0; oloop < fv.size(); oloop++) {
            v = (int[]) (fv.get(oloop));
            n = (int[]) (fn.get(oloop));
            for (int iloop = 0; iloop < v.length; iloop++) {
                //fill in the normal coordinate data
                System.arraycopy((float[]) vnData.get(n[iloop] - 1), 0, coords, 0, coords.length);
                modeldata.put(coords);
                //fill in the vertex coordinate data
                System.arraycopy((float[]) vData.get(v[iloop] - 1), 0, coords, 0, coords.length);
                modeldata.put(coords);
            }
        }
        modeldata.position(0);
    }

    private void ConstructV() {
        int[] v;
        float coords[] = new float[3];
        int fbSize = PolyCount * (FaceMultiplier * 3); //3v per poly
        modeldata = GLBuffers.newDirectFloatBuffer(fbSize);
        modeldata.position(0);
        for (int[] fv1 : fv) {
            v = (int[]) (fv1);
            for (int iloop = 0; iloop < v.length; iloop++) {
                //fill in the vertex coordinate data
                System.arraycopy((float[]) vData.get(v[iloop] - 1), 0, coords, 0, coords.length);
                modeldata.put(coords);
            }
        }
        modeldata.position(0);
    }

    public void drawModel(GL2 inGL) {
        if (init) {
            ConstructInterleavedArray(inGL);
            cleanup();
            init = false;
        }
        inGL.glDrawArrays(FaceFormat, 0, PolyCount * FaceMultiplier);
    }

    private void cleanup() {
        vData.clear();
        vtData.clear();
        vnData.clear();
        fv.clear();
        ft.clear();
        fn.clear();
        modeldata.clear();
    }

    public static int loadWavefrontObjectAsDisplayList(GL2 inGL, String inFileName) {
        return loadWavefrontObjectAsDisplayList(inGL, inFileName, false);
    }

    public static int loadWavefrontObjectAsDisplayList(GL2 inGL, String inFileName, boolean normalize) {
        if (!normalize) {
            return loadWavefrontObjectAsDisplayList(inGL, inFileName,
                    new float[]{0f, 0f, 0f},
                    1f,
                    new float[]{0f, 0f, 0f}
            );
        } else {
            return loadWavefrontObjectAsDisplayList(inGL, inFileName,
                    null,
                    1f,
                    null
            );
        }
    }

    public static int loadWavefrontObjectAsDisplayList(GL2 inGL, String inFileName,
            float[] min, float maxRange, float[] padding) {
        int tDisplayListID = inGL.glGenLists(1);
        WavefrontObjectLoader_DisplayList tWaveFrontObjectModel = new WavefrontObjectLoader_DisplayList(inFileName);
        if (min == null || padding == null) {
            tWaveFrontObjectModel.normalizeVertices();
        } else {
            tWaveFrontObjectModel.normalizeVertices(min, maxRange, padding);
        }
        inGL.glNewList(tDisplayListID, GL_COMPILE);
        tWaveFrontObjectModel.drawModel(inGL);
        inGL.glEndList();
        return tDisplayListID;
    }

    private void normalizeVertices() {
        // scale everything to be in range [0,...]
        final int N = 3;
        float[] min = {Float.MAX_VALUE, Float.MAX_VALUE, Float.MAX_VALUE};
        float[] max = {Float.MIN_VALUE, Float.MIN_VALUE, Float.MIN_VALUE};
        for (float[] v : vData) {
            for (int i = 0; i < N; i++) {
                min[i] = (v[i] < min[i]) ? v[i] : min[i];
                max[i] = (v[i] > max[i]) ? v[i] : max[i];
            }
        }

        for (float[] v : vData) {
            for (int i = 0; i < N; i++) {
                v[i] = v[i] - min[i];
            }
        }

        // find the highest max value for each dimension
        float maxRange = Float.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            max[i] -= min[i];
            maxRange = max[i] > maxRange ? max[i] : maxRange;
        }

        // scale from the max value to [0,1]
        for (float[] v : vData) {
            for (int i = 0; i < N; i++) {
                v[i] = v[i] / maxRange;
            }
        }

        final float[] padding = new float[N];
        // center in X and Y direction
        for (int i = 0; i < 2; i++) {
            max[i] = max[i] / maxRange;
            final float empty = 1 - max[i];
            padding[i] = empty / 2;
        }

        for (float[] v : vData) {
            for (int i = 0; i < N; i++) {
                v[i] += padding[i];
            }
        }
    }

    private void normalizeVertices(final float[] min, final float maxRange,
            final float[] padding) {
        // scale everything to be in range [0,...]
        final int N = 3;

        for (float[] v : vData) {
            for (int i = 0; i < N; i++) {
                v[i] = v[i] - min[i];
            }
        }

        // scale from the max value to [0,1]
        for (float[] v : vData) {
            for (int i = 0; i < N; i++) {
                v[i] = v[i] / maxRange;
            }
        }

        // center in X and Y direction
        for (float[] v : vData) {
            for (int i = 0; i < N; i++) {
                v[i] += padding[i];
            }
        }
    }

}
