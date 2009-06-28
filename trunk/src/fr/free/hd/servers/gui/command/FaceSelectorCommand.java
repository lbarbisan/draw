package fr.free.hd.servers.gui.command;

import org.springframework.richclient.command.support.ApplicationWindowAwareCommand;

import fr.free.hd.servers.entities.Face;
import fr.free.hd.servers.gui.PhonemView;

public class FaceSelectorCommand extends ApplicationWindowAwareCommand  {

	private Face face;
	
	public FaceSelectorCommand()
	{
		super("FaceSelectorCommand");
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}


	public FaceSelectorCommand(Face face)
	{
		super(face.getPicture());
		this.face = face;
	}
	

	/**
	 * @see org.springframework.richclient.command.ActionCommand#doExecuteCommand()
	 */
	@Override
	protected void doExecuteCommand() {
		PhonemView view = (PhonemView)getApplicationWindow().getPage().getView("PhonemView");
		view.setFace(face);
	}

}
