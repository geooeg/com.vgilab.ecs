package com.vgilab.ecs.service;

import com.google.common.io.Files;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.apache.commons.io.IOUtils;
import org.geotools.data.DefaultTransaction;
import org.geotools.data.Transaction;
import org.geotools.data.collection.ListFeatureCollection;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.simple.SimpleFeatureStore;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Zhang
 */
@Service
public class ShapefileService {
    public String FILENAME_ALL = "ecs-all";
    public String FILENAME_AVERAGE = "ecs-average";
    public String SHAPEFILE_EXTENSION = ".shp";
    public String ARCHIVE_EXTENSION = ".zip";

    @Autowired
    private FeatureService featureService;

    public File exportShapefileWithAllFeatures() {
        return this.exportShapefile(FILENAME_ALL, this.featureService.getFeatureType(), this.featureService.exportAllFeatures());

    }
    
    public File exportShapefileWithAllFeaturesAndAverageAltitude() {
        return this.exportShapefile(FILENAME_AVERAGE, this.featureService.getFeatureType(), this.featureService.exportAllFeaturesWithAverageAltitude());
    }
    
    private File exportShapefile(String filename, SimpleFeatureType featureType, List<SimpleFeature> exportAllFeatures) {
        final File shapeDir = Files.createTempDir();
        final File shapeFile = new File(shapeDir, filename + SHAPEFILE_EXTENSION);
        // LOG.debug("writing out shapefile {}", shapeFile);
        try {
            final ShapefileDataStoreFactory dataStoreFactory = new ShapefileDataStoreFactory();
            final Map<String, Serializable> params = new HashMap<>();
            params.put("url", shapeFile.toURI().toURL());
            params.put("create spatial index", Boolean.TRUE);
            final ShapefileDataStore shapefileDataStore = (ShapefileDataStore) dataStoreFactory.createNewDataStore(params);
            shapefileDataStore.createSchema(featureType);
            final Transaction transaction = new DefaultTransaction("create");
            final String typeName = shapefileDataStore.getTypeNames()[0];
            final SimpleFeatureSource featureSource = shapefileDataStore.getFeatureSource(typeName);
            if (featureSource instanceof SimpleFeatureStore) {
                SimpleFeatureStore featureStore = (SimpleFeatureStore) featureSource;
                /*
                 * SimpleFeatureStore has a method to add features from a
                 * SimpleFeatureCollection object, so we use the ListFeatureCollection
                 * class to wrap our list of features.
                 */
                final SimpleFeatureCollection collection = new ListFeatureCollection(featureType, exportAllFeatures);
                featureStore.setTransaction(transaction);
                try {
                    featureStore.addFeatures(collection);
                    transaction.commit();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    transaction.rollback();
                } finally {
                    transaction.close();
                }
            } else {
                System.out.println(typeName + " does not support read/write access");
            }
            shapeDir.deleteOnExit(); // Note: the order is important
            final File zipFile = new File(shapeDir + File.pathSeparator + filename + ARCHIVE_EXTENSION);
            try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile))) {
                for (File file : shapeDir.listFiles()) {
                    file.deleteOnExit();
                    // ZipEntry entry = new ZipEntry(shapeDir.replace(rootDir, "") + file.getName());
                    final ZipEntry entry = new ZipEntry(file.getName());
                    zipOutputStream.putNextEntry(entry);

                    final FileInputStream in = new FileInputStream(file);
                    IOUtils.copy(in, zipOutputStream);
                    IOUtils.closeQuietly(in);
                }
                zipOutputStream.flush();
            }
            return zipFile;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ShapefileService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(ShapefileService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ShapefileService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String getArchiveFilenameAll() {
        return FILENAME_ALL + ARCHIVE_EXTENSION;
    }
    
    public String getArchiveFilenameAverageAltitude() {
        return FILENAME_AVERAGE + ARCHIVE_EXTENSION;
    }

}
