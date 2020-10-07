package moscow.exchange.data.db.dao;

 class ColumnDB {

    public final int columnNumber;
    public final String columnName;
    public final Class columnType;

    public ColumnDB(int columnNumber, String columnName, Class columnType) {
        this.columnNumber = columnNumber;
        this.columnName = columnName;
        this.columnType = columnType;
    }
}
