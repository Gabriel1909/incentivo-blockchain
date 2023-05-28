package br.unisinos.blockchain.config;

import org.hyperledger.fabric.gateway.*;
import org.hyperledger.fabric.gateway.impl.identity.X509IdentityImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import static br.unisinos.blockchain.domain.Constants.*;

@Configuration
public class GatewayConfig {

    @Bean
    public Gateway gateway() throws IOException, CertificateException, InvalidKeyException {
        System.setProperty("org.hyperledger.fabric.sdk.service_discovery.as_localhost", "true");

        Wallet wallet = Wallets.newFileSystemWallet(Paths.get(WALLET));
        Path networkConfigPath = Paths.get(HOSPITAL_PATH + "connection.yaml");

        PrivateKey privateKey = Identities.readPrivateKey(new String(Files.readAllBytes(Paths.get(ADMIN_MSP + "keystore/priv_sk"))));
        X509Certificate certificate = Identities.readX509Certificate(new String(Files.readAllBytes(Paths.get(ADMIN_MSP + "signcerts/Admin@hospital1-cert.pem"))));
        Identity adminIdentity = new X509IdentityImpl(MSP, certificate, privateKey);

        wallet.put(ADMIN, adminIdentity);

        return Gateway.createBuilder()
                .identity(wallet, ADMIN)
                .networkConfig(networkConfigPath)
                .discovery(true)
                .connect();
    }

    @Bean
    public Network network(Gateway gateway) {
        return gateway.getNetwork(CHANNEL);
    }
}