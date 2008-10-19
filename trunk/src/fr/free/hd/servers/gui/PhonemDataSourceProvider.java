package fr.free.hd.servers.gui;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSourceProvider;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import fr.free.hd.servers.entities.HandKeyEnum;
import fr.free.hd.servers.entities.HandPositionEnum;
import fr.free.hd.servers.entities.MouthVowelEnum;
import fr.free.hd.servers.entities.Phonem;

public class PhonemDataSourceProvider extends JRAbstractBeanDataSourceProvider {
	 
    public PhonemDataSourceProvider() {
            super(Phonem.class);
    }

    public JRDataSource create(JasperReport report) throws JRException {
            List<Phonem> list = new ArrayList<Phonem>();
            Phonem phonem = new Phonem("TEST", HandKeyEnum.HAND_KEY_1M, HandPositionEnum.HAND_POSITION_BOUCHE,
        			MouthVowelEnum.MOUTH_VOWEL_A);
            list.add(phonem);
            phonem = new Phonem("TEST2", HandKeyEnum.HAND_KEY_1M, HandPositionEnum.HAND_POSITION_BOUCHE,
        			MouthVowelEnum.MOUTH_VOWEL_A);
            list.add(phonem);
            return new JRBeanCollectionDataSource(list);
    }
    
    public void dispose(JRDataSource dataSource) throws JRException {
            // nothing to dispose
    }
}
