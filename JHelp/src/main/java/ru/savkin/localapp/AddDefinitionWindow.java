package ru.savkin.localapp;


import ru.savkin.API;
import ru.savkin.Definition;
import ru.savkin.DocumentReader;

public final class AddDefinitionWindow extends  DefinitionWindow {

    public AddDefinitionWindow(Window frame) {
        super(frame, "Add new definition");
        setWindows();
    }

    @Override
    protected void okMethod() {
        String def = getFrame().getAddDefinitionWindow().getDefField().getText();
        String desc = getFrame().getAddDefinitionWindow().getEditorPane().getText();
        Definition definition = new Definition(def, desc);
        API.add(definition);
        setVisible(false);
    }
}
