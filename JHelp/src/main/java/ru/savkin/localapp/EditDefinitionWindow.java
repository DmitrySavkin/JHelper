package ru.savkin.localapp;

import ru.savkin.DocumentReader;

public final class EditDefinitionWindow extends DefinitionWindow {



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
      //  super.getDocumentReader().editDefinition();
       // super.setVisible(false);
    }

}
