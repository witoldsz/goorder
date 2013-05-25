package net.goorder.db;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;
import javax.sql.DataSource;
import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

/**
 *
 * @author witoldsz
 */
public class GoorderDbUtils {

    private static Properties properties;

    public static DSLContext jooq(DataSource dataSource) {
        return DSL.using(dataSource, SQLDialect.DERBY);
    }

    public static void applyMigrations(Connection conn) {
        try {
            String changeLogFile = properties().getProperty("goorder.liquibase.changelog");
            ResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor(GoorderDbUtils.class.getClassLoader());
            JdbcConnection databaseConnection = new JdbcConnection(conn);
            new Liquibase(changeLogFile, resourceAccessor, databaseConnection).update(null);
        } catch (LiquibaseException ex) {
            throw new RuntimeException(ex);
        }
    }

        private static Properties properties() {
        if (properties == null) {
            properties = readProperties();
        }
        return properties;
    }

    private static Properties readProperties() {
        try {
            Properties env = new Properties();
            try (InputStream is = GoorderDbUtils.class.getResourceAsStream("/goorder.properties")) {
                env.load(is);
            }
            return env;
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
