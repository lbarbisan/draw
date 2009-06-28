package fr.free.hd.servers.gui.command;

import org.springframework.richclient.command.ActionCommand;
import org.springframework.richclient.command.support.ApplicationWindowAwareCommand;

import fr.free.hd.servers.entities.Face;
import fr.free.hd.servers.gui.PhonemView;

public class FaceSelectorCommand extends ActionCommand  {

	private Face face;
	private PhonemView view;
	@Override
	public boolean isEnabled() {
		return true;
	}


	public FaceSelectorCommand(PhonemView view, Face face, String name)
	{
		super(name);
		this.face = face;
		this.view = view;
	}
	

	/**
	 * @see org.springframework.richclient.command.ActionCommand#doExecuteCommand()
	 */
	@Override
	protected void doExecuteCommand() {
		view.setFace(face);
	}

}
