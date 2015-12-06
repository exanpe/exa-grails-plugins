package fr.exanpe.grails.datatables.model

class DatatableModel {

    List<DatatableModelCell> headers = []
    List<DatatableModelRow> rows = []

    void addHeaders(List<String> columns) {
        columns.each { column ->
            headers.add(new DatatableModelCell(name: column, value: column))
        }
    }

    void updateHeader(String name, String value) {
        def headerIdx = headers.findIndexOf { it.name == name }
        if (headerIdx >= 0) {
            headers?.get(headerIdx)?.isCustom = true
            headers?.get(headerIdx)?.value = value
        }
    }
}

class DatatableModelRow {

    Map<String, DatatableModelCell> cells = new LinkedHashMap<String, DatatableModelCell>()

    void addCell(String name, String value, boolean hidden = false) {
        cells.put(name, new DatatableModelCell(name: name, value: value, hidden: hidden))
    }

    void updateCell(String name, String value) {
        if (cells.containsKey(name)) {
            cells.get(name).isCustom = true
            cells.get(name).value = value
        }
    }
}

class DatatableModelCell {

    String name
    String value
    boolean isCustom
    boolean hidden
}