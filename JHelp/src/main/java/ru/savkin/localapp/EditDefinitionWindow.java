package ru.savkin.localapp;

import ru.savkin.API;
import ru.savkin.Definition;
import ru.savkin.DocumentReader;

/**
 * This is the dialog to edit the term.
 * @version  1.0
 * @author dmitrysavkin
 */
public final class EditDefinitionWindow extends DefinitionWindow {


    /**
     * Creates new dialog to manipulate the existing frame
     * @param frame the main frame
     */
    public EditDefinitionWindow(Window frame) {
        super(frame, "Редактировать термин");
        setWindows();
    }

    @Override
    public void init() {
        String def = super.getFrame().getDefField().getText();
        String discript = this.getFrame().getTextArea().getText();
        super.getDefField().setText(def);
        super.getEditorPane().setText(discript);
        setVisible(true);
    }

    @Override
    protected  void okMethod() {
      String def =  getFrame().getEditDefinitionWindow().getDefField().getText();
      String desc = getFrame().getEditDefinitionWindow().getEditorPane().getText();
        Definition definition = new Definition(def, desc);
      API.edit(definition);
    }

}
