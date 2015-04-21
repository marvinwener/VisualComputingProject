/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.tue.win.vcp.virtualbreitenbergenvironment.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import static javax.media.opengl.GL.GL_FRONT_AND_BACK;
import javax.media.opengl.GL2;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_AMBIENT;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_DIFFUSE;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_SHININESS;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_SPECULAR;

/**
 *
 * @author maikel
 */
public class MTLParsing {

    public class Material {

        private final String name;
        private float shininess;
        private float[] ambient;
        private float[] diffuse;
        private float[] specular;
        private int illum;
        private float transparency;

        public Material(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Material{" + "name=" + name + ", shininess=" + shininess + ", ambient=" + Arrays.toString(ambient) + ", diffuse=" + Arrays.toString(diffuse) + ", specular=" + Arrays.toString(specular) + ", illum=" + illum + ", transparency=" + transparency + '}';
        }

        public void activate(GL2 gl) {
            setMaterial(gl, new float[] {
                ambient[0], ambient[1], ambient[2], 1.0f, //ambient
            diffuse[0], diffuse[1], diffuse[2], 1.0f, //diffuse
            specular[0], specular[1], specular[2], 1.0f, //specular 
            shininess //shininess
            });
        }
        
        /**
     * Parses an array and sets the given parameters for the ambient, diffuse,
     * specular and shininess values of the material.
     *
     * @param material An array with 13 floats where the first 4 values
     * represents the ambient, the next 4 the diffuse factor, the next 4 the
     * specular factor, and the last the shininess value of the material.
     */
    private void setMaterial(GL2 gl, float[] material) {
        gl.glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT, material, 0);
        gl.glMaterialfv(GL_FRONT_AND_BACK, GL_DIFFUSE, material, 4);
        gl.glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, material, 8);
        gl.glMaterialfv(GL_FRONT_AND_BACK, GL_SHININESS, material, 12);
    }

    }

    Material m;
    List<Material> materials = new ArrayList<>();
    
    public static List<Material> parse(String filename) throws FileNotFoundException {
        return parse(new File(filename));
    }

    public static List<Material> parse(File source) throws FileNotFoundException {
        return parse(new FileInputStream(source));
    }
    
    public static List<Material> parse(InputStream source) {
        return new MTLParsing().parseThis(source);
    }

    public List<Material> parseThis(InputStream source) {
        materials.clear();
        Scanner s = new Scanner(source);
        while (s.hasNext()) {
            parseLine(s.nextLine());
        }
        return materials;
    }

    private void parseLine(String line) {
        final String[] split = line.split(" ");
        if (line.isEmpty()) {
            //ignore
        } else if (line.startsWith("#")) {
            //ignore
        } else if (line.startsWith("newmtl ")) {
            m = new Material(split[split.length - 1]);
            materials.add(m);
        } else if (line.startsWith("Ns ")) {
            m.shininess = Float.parseFloat(split[1]);
        } else if (line.startsWith("Ka ")) {
            m.ambient = parseThreeNumbers(split);
        } else if (line.startsWith("Kd ")) {
            m.diffuse = parseThreeNumbers(split);
        } else if (line.startsWith("Ks ")) {
            m.specular = parseThreeNumbers(split);
        } else if (line.startsWith("illum ")) {
            m.illum = Integer.parseInt(split[1]);
        } else if (line.startsWith("d ") || line.startsWith("Tr ")) {
            m.transparency = Float.parseFloat(split[1]);
        }
    }

    private float[] parseThreeNumbers(String[] split) {
        float[] result = new float[3];
        for (int i = 1; i <= 3; i++) {
            result[i - 1] = Float.parseFloat(split[i]);
        }
        return result;
    }
}
