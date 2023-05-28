package br.unisinos.blockchain.domain;

public class Constants {

    private Constants() {
    }

    //Chaincodes
    public static final String GLOBAL = "global";
    public static final String RELACIONAMENTO = "relacionamento";

    //Metodos
    public static final String ADICIONAR_RELACIONAMENTO = "adicionarRelacionamento";
    public static final String BUSCAR_PARTICIPANTE = "buscarParticipante";
    public static final String ADICIONAR_PARTICIPANTE = "adicionarParticipante";
    public static final String ADICIONAR_SIGNIFICANCIA = "adicionarSignificancia";
    public static final String BUSCAR_GLOBAL = "buscarGlobal";

    //Informacoes blockchain
    public static final double SIGNIFICANCIA_INICIAL = 1.0;
    public static final double FRACAO_SIGNIFICANCIA = 0.5;
    public static final String CHANNEL = "mychannel";
    public static final String PROVEDOR = "PROVEDOR";
    public static final String ADMIN = "admin";
    public static final String MSP = "Hospital1MSP";
    public static final String WALLET = "wallet";
    public static final String HOSPITAL_PATH = "../contratos/crypto-config/peerOrganizations/hospital1/";
    public static final String ADMIN_MSP = HOSPITAL_PATH + "users/Admin@hospital1/msp/";
}
