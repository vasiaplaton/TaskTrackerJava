package platon.ru.vsu.cs.database.sql.table;

public class QueryBuilder {
    protected final String[] columnNames;
    protected final String tablename;

    public QueryBuilder(String[] columnNames, String tablename) {
        this.columnNames = columnNames;
        this.tablename = tablename;
    }

    protected StringBuilder getColumnCommaDivided(){
        StringBuilder queryString = new StringBuilder();
        for (int i = 0; i < columnNames.length; i++) {
            String column = columnNames[i];

            queryString.append(column);

            if (i != columnNames.length - 1) {
                queryString.append(", ");
            }
        }
        return queryString;
    }

    protected static StringBuilder buildWhere(Filter[] filters){
        if (filters.length == 0) {
            return new StringBuilder();
        }
        StringBuilder queryString = new StringBuilder();
        queryString.append(" WHERE ");
        for (int i = 0; i < filters.length; i++) {
            Filter filter = filters[i];
            queryString.append(filter.columnName);
            queryString.append(" = ");
            queryString.append(filter.columnValue);
            if (i != filters.length - 1) {
                queryString.append(" AND ");
            }
        }
        return queryString;
    }

    public String select(Filter[] filters){
        StringBuilder queryString = new StringBuilder("SELECT ");
        queryString.append("id, ");
        queryString.append(getColumnCommaDivided());
        queryString.append(" FROM ").append(tablename);

        if (filters.length > 0) {
            queryString.append(buildWhere(filters));
        }

        return queryString.toString();
    }

    public String insert(Integer id, String[] columnValues){
        StringBuilder queryString = new StringBuilder("INSERT INTO ");
        queryString.append(tablename).append(" (");

        if(id != null) {
            queryString.append("id, ");
        }

        queryString.append(getColumnCommaDivided()).append(" ) VALUES (");

        if(id != null) {
            queryString.append(id).append(", ");
        }

        for (int i = 0; i < columnValues.length; i++) {
            String column = columnValues[i];
            queryString.append("'");
            queryString.append(column);
            queryString.append("'");
            if (i != columnValues.length - 1) {
                queryString.append(", ");
            }
        }
        queryString.append(" );");

        return queryString.toString();
    }

    public String delete(int id){
        return "DELETE FROM " + tablename + " where id = " + id;
    }

    public String update(String[] newerColumnValues, int id){
        StringBuilder queryString = new StringBuilder("UPDATE ").append(tablename).append(" SET ");

        for (int i = 0; i < columnNames.length; i++) {
            String column = columnNames[i];
            queryString.append(column);
            queryString.append(" = ");
            queryString.append(newerColumnValues[i]);

            if (i != columnNames.length - 1) {
                queryString.append(", ");
            }
        }

        queryString.append(" where id = ").append(id);

        return queryString.toString();
    }

}
