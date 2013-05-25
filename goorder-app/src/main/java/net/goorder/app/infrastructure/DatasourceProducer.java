package net.goorder.app.infrastructure;

import java.sql.Connection;
import java.sql.SQLException;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;
import net.goorder.db.GoorderDbUtils;
import org.jooq.DSLContext;

/**
 *
 * @author witoldsz
 */
@Startup
@Singleton
@Lock(LockType.READ)
public class DatasourceProducer {

    @Resource(name = "jdbc/goorder")
    private DataSource goorderDs;

    @Resource(name = "jdbc/goorder_raw")
    private DataSource goorderRawDs;

    @PostConstruct
    public void postConstruct() {
        try (Connection conn = goorderRawDs.getConnection()) {
            GoorderDbUtils.applyMigrations(conn);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Produces
    public DSLContext jooq() {
        return GoorderDbUtils.jooq(goorderDs);
    }
}
