package com.brodma.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.io.Serializable;

@Component
@ConfigurationProperties(prefix = "hibernate.ogm.mongodb")
public class MongoProperty implements Serializable {

    private String provider;
    private String host;
    private String database;
    private String username;
    private String password;
    private String connectionTimeout;
    private String associationDocumentStorageType;
    private String associationStorageType;
    private String writeconcern;
    private String packagesToScan;
    private String enabled;
    private String gridDialect;
    private String createDatabase;
    private String errorHandler;
    private String readPreference;
    private String mapStorageType;
    private String puName;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(String connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public String getWriteconcern() {
        return writeconcern;
    }

    public void setWriteconcern(String writeconcern) {
        this.writeconcern = writeconcern;
    }

    public String getPackagesToScan() {
        return packagesToScan;
    }

    public void setPackagesToScan(String packagesToScan) {
        this.packagesToScan = packagesToScan;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getGridDialect() {
        return gridDialect;
    }

    public void setGridDialect(String gridDialect) {
        this.gridDialect = gridDialect;
    }

    public String getCreateDatabase() {
        return createDatabase;
    }

    public void setCreateDatabase(String createDatabase) {
        this.createDatabase = createDatabase;
    }

    public String getErrorHandler() {
        return errorHandler;
    }

    public void setErrorHandler(String errorHandler) {
        this.errorHandler = errorHandler;
    }

    public String getReadPreference() {
        return readPreference;
    }

    public void setReadPreference(String readPreference) {
        this.readPreference = readPreference;
    }

    public String getAssociationDocumentStorageType() {
        return associationDocumentStorageType;
    }

    public void setAssociationDocumentStorageType(String associationDocumentStorageType) {
        this.associationDocumentStorageType = associationDocumentStorageType;
    }

    public String getAssociationStorageType() {
        return associationStorageType;
    }

    public void setAssociationStorageType(String associationStorageType) {
        this.associationStorageType = associationStorageType;
    }

    public String getMapStorageType() {
        return mapStorageType;
    }

    public void setMapStorageType(String mapStorageType) {
        this.mapStorageType = mapStorageType;
    }

    public String getPuName() {
        return puName;
    }

    public void setPuName(String puName) {
        this.puName = puName;
    }

}
