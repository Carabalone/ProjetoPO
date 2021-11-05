package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.*;

import java.io.FileNotFoundException;
import java.io.IOException;

import ggc.core.WarehouseManager;
//FIXME import classes
import ggc.core.exception.MissingFileAssociationException;

/**
 * Save current state to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<WarehouseManager> {

  /** @param receiver */
  DoSaveFile(WarehouseManager receiver) {
    super(Label.SAVE, receiver);
    // if (_receiver.getFileName() == null)
    //   addStringField("filename", Message.newSaveAs());
  }

  @Override
  public final void execute() throws CommandException {
    if (_receiver.alteredSinceLastSave() || true){
      try{
        if (_receiver.getFileName().equals("")){
          Form form = new Form("filename");
          form.addStringField("filename", Message.newSaveAs());
          form.parse();
          String filename = form.stringField("filename");
          _receiver.saveAs(filename);
        } else{
          _receiver.save();
        }
      } catch (IOException ex){
        ex.printStackTrace();
      } catch (MissingFileAssociationException ex){
        _display.popup("error (FIXME-> resolve exception)");
      }

    }
  }

}
