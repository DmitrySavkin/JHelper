package ru.savkin;

public final class API {

    private final static DocumentReader  documentReader = DocumentReader.getDocumentReader();

    public static final Definition getSearchResult(String definition) {
        for (Definition def : documentReader.getBookedDatas()) {
            if (def.getDefinition().equalsIgnoreCase(definition)) {
                return new Definition(definition, def.getDescription());
            }
        }
      return  null;
    }

    public static final void remove(Definition definition) {
        documentReader.removeNumber(definition.getDefinition());
    }

    public static final void add(Definition definition) {
        documentReader.addData(definition);
    }

    public static final void edit(Definition definition) {
        documentReader.editData(definition);
    }


}
