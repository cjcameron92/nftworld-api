package org.amirion.nftworld.property;

public class ContractProps {

    private String polygonPlayerContract;
    private String polygonWrldContract;
    private String ethereumWrldContract;

    public ContractProps() {
        polygonPlayerContract = "";
        polygonWrldContract = "";
        ethereumWrldContract = "";
    }


    public String getPolygonPlayerContract() {
        return polygonPlayerContract;
    }

    public void setPolygonPlayerContract(String polygonPlayerContract) {
        this.polygonPlayerContract = polygonPlayerContract;
    }

    public String getPolygonWrldContract() {
        return polygonWrldContract;
    }

    public void setPolygonWrldContract(String polygonWrldContract) {
        this.polygonWrldContract = polygonWrldContract;
    }

    public String getEthereumWrldContract() {
        return ethereumWrldContract;
    }

    public void setEthereumWrldContract(String ethereumWrldContract) {
        this.ethereumWrldContract = ethereumWrldContract;
    }
}
