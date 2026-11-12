package view;

import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.OrdemServico;
import util.Conversor;

public class TabelaOrdens {

    public static void preencher(JTable tabela, List<OrdemServico> ordens) {
        DefaultTableModel modelo = (DefaultTableModel) tabela.getModel();
        modelo.setRowCount(0);
        for (OrdemServico os : ordens) {
            modelo.addRow(new Object[] { os.getNumero(), Conversor.formatarData(os.getDataAbertura()),
                    os.getCliente().getNome(), os.getBateria().getNumeroSerie(), os.getTipo(),
                    os.getTecnico().getNome(), os.getStatus(), Conversor.formatarData(os.getDataConclusao()),
                    Conversor.formatarValor(os.getValorTotal()) });
        }
    }
}
