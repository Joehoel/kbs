package com.ictm2n2.resources.database;

public class Query {
    private StringBuilder query;

    /**
     *
     * @param table
     * @return
     */
    public Query delete(String table) {
        query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(table);
        return this;
    }

    /**
     * Adds the WHERE condition to the SQL query
     *
     * @param requirement
     * @return
     */
    public Query where(String requirement) {
        query.append(" WHERE ");
        query.append(requirement);
        return this;
    }

    /**
     *
     * @param table
     * @return
     */
    public Query from(String table) {
        query.append(" FROM ");
        query.append(table);
        return this;
    }

    /**
     *
     * @param table
     * @return
     */
    public Query update(String table) {
        query = new StringBuilder();
        query.append("UPDATE ");
        query.append(table);
        query.append(" SET ");
        return this;
    }

    /**
     * Adds columns
     *
     * @param Columns
     */
    public Query set(String[] columns) {
        int count = columns.length;
        if (count == 0)
            throw new IllegalArgumentException("Invalid argument count");

        for (String column : columns) {
            query.append(column);
            query.append(" = ");
            query.append("?");
            query.append(",");
        }
        query.deleteCharAt(query.lastIndexOf(","));
        return this;
    }

    /**
     *
     * @param table
     * @return
     */
    public Query insert(String table) {
        query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(table);
        return this;
    }

    /**
     *
     * @param params
     * @return
     */
    public Query values(Object[] params) {
        query.append(" VALUES(");

        int count = params.length;

        if (count == 0)
            throw new IllegalArgumentException("Invalid parameter count");

        for (int i = 0; i < count; i++) {
            query.append("?,");
        }
        // removes the last comma
        query.deleteCharAt(query.lastIndexOf(","));
        query.append(");");
        return this;
    }

    /**
     *
     * @param columns
     * @return
     */
    public Query select(Object[] columns) {
        query = new StringBuilder();
        query.append("SELECT ");
        if (columns != null) {
            for (Object column : columns) {
                query.append(column);
                query.append(",");
            }
            // removes the last question mark
            query.deleteCharAt(query.lastIndexOf(","));
        } else
            query.append("*");

        return this;
    }

    /**
     *
     * @param columns
     * @return
     */
    public Query columns(Object[] columns) {
        // query = new StringBuilder();
        query.append(" (");
        for (Object column : columns) {
            query.append(column);
            query.append(", ");
        }
        query.deleteCharAt(query.lastIndexOf(", "));
        query.append(")");
        return this;
    }

    /**
     * Returns the generated SQL query
     *
     * @return query
     */
    public String getQuery() {
        return query.toString();
    }

    /**
     * Returns the generated SQL query
     *
     * @return query
     */
    public Query groupBy(String column) {
        query = new StringBuilder();
        query.append(" GROUP BY ");
        query.append(column);
        ;
        return this;
    }

    public Query DbMonitorPanelQuery() {
        query = new StringBuilder();

        query.append("SELECT c.hostname, c.cpu, c.opslag, s.beschikbaar, s.tijdstip " +
                "FROM component c " +
                "JOIN status s " +
                "ON c.component_id=s.component_id WHERE c.type_id IN (3,4,5)" +
                "GROUP BY c.component_id " +
                "HAVING max(tijdstip);");

        return this;
    }

    public Query WbMonitorPanelQuery() {
        query = new StringBuilder();

        query.append("SELECT c.hostname, c.cpu, c.opslag, s.beschikbaar, s.tijdstip " +
                "FROM component c " +
                "JOIN status s " +
                "ON c.component_id=s.component_id WHERE c.type_id IN (6,7,8)" +
                "GROUP BY c.component_id " +
                "HAVING max(tijdstip);");

        return this;
    }

    public Query LbMonitorPanelQuery() {
        query = new StringBuilder();

        query.append("SELECT c.hostname, c.cpu, c.opslag, s.beschikbaar, s.tijdstip " +
                "FROM component c " +
                "JOIN status s " +
                "ON c.component_id=s.component_id WHERE c.type_id IN (2)" +
                "GROUP BY c.component_id " +
                "HAVING max(tijdstip);");

        return this;
    }

    public Query PfSMonitorPanelQuery() {
        query = new StringBuilder();

        query.append("SELECT c.hostname, c.cpu, c.opslag, s.beschikbaar, s.tijdstip " +
                "FROM component c " +
                "JOIN status s " +
                "ON c.component_id=s.component_id WHERE c.type_id IN (1)" +
                "GROUP BY c.component_id " +
                "HAVING max(tijdstip);");

        return this;
    }

    public Query DetailOverzichtMonitorPanelQuery(String hostname) {
        query = new StringBuilder();
        query.append(
                "SELECT c.hostname, c.cpu, s.processor_belasting, c.opslag, s.opslag_verbruik, s.beschikbaar_lengte, s.tijdstip\n"
                        + "FROM component c\n" + "JOIN status s\n"
                        + "ON c.component_id = s.component_id WHERE c.hostname = \"" + hostname + "\"\n"
                        + "GROUP BY c.component_id\n" + "HAVING max(tijdstip);");
        return this;
    }

    public Query LastInsertedId() {
        query = new StringBuilder();
        query.append("SELECT configuratie_id FROM configuratie ORDER BY datum DESC LIMIT 1");
        return this;
    }

    public Query InsertOnderdeel(String table) {
        query = new StringBuilder();
        query.append("INSERT INTO " + table);
        return this;
    }
}
