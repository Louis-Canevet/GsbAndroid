package fr.louis.gsb;


// Classe public Echantillon
public class Echantillon implements java.io.Serializable {
    private String code;
    private String label;
    private String quantiteStock;

    public Echantillon(String code, String label, String quantiteStock) {
        this.code = code;
        this.label = label;
        this.quantiteStock = quantiteStock;
    }

    public Echantillon(String code, int quantiteStock) {

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getQuantiteStock() {
        return quantiteStock;
    }

    public void setQuantiteStock(String quantiteStock) {
        this.quantiteStock = quantiteStock;
    }

}
