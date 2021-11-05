package ggc.app.main;

import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import ggc.core.WarehouseManager;
//FIXME import classes

import ggc.core.exception.UnavailableFileException;
import ggc.app.exception.FileOpenFailedException;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Open existing saved state.
 */
class DoOpenFile extends Command<WarehouseManager> {

  /** @param receiver */
  DoOpenFile(WarehouseManager receiver) {
    super(Label.OPEN, receiver);
    addStringField("filename", Message.openFile());
  }

  @Override
  public final void execute() throws CommandException, FileOpenFailedException {
    try {
      String filename = stringField("filename");
      _receiver.load(filename);
    } catch (UnavailableFileException ufe) {
      throw new FileOpenFailedException(ufe.getFilename());
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

}
