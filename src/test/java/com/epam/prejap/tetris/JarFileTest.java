package com.epam.prejap.tetris;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

@Test(groups = "JarFile")
public class JarFileTest {

    private static final String expectedMainClassName = Tetris.class.getCanonicalName();

    @Test(dataProvider = "manifestForCheckingJar", dependsOnMethods = "dataProviderTest")
    public void shallContainsSameMainClassName(Manifest manifest) {
        //given
        Attributes mainAttributes = manifest.getMainAttributes();

        //when
        String actualJarMainClassName = mainAttributes.getValue("Main-Class");

        //then
        assertEquals(actualJarMainClassName, expectedMainClassName,
                String.format("Manifest file shall contains %s as main class name, but did not",
                        expectedMainClassName));
    }

    @DataProvider
    public static Object[][] manifestForCheckingJar() {
        String outputPathProperty = System.getProperty("outputPath");
        Path outputPath = Path.of(outputPathProperty);

        Manifest manifest = null;
        try {
            JarFile jarFile = new JarFile(outputPath.toString());
            manifest = jarFile.getManifest();
        } catch (IOException e) {
            fail(".jar or MANIFEST.MF file does not exist");
        }
        return new Object[][]{{manifest}};
    }

    @Test
    public void dataProviderTest() {
        manifestForCheckingJar();
    }

}
