package iDoge.Graphics.Other;

import iDoge.Graphics.Page;
import javafx.event.ActionEvent;

import java.io.IOException;

public class Sign_inController extends Page {
    public Sign_inController(ActionEvent event, String fxmlPath) throws IOException {
        super(event, fxmlPath);
    }

    @Override
    public void setKnownFields() {

    }

    @Override
    public <myInput extends Page.input> void setUnknownFields(myInput i) {

    }

    @Override
    public void otherGraphics() {

    }

    public static class input extends Page.input{

        public input() {}

        @Override
        public void useInputs() {}
    }
}
