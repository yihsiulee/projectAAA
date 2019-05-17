package com.allpass.projectAAA.Service;

import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;

public class wallet {

    public void createWallte(){
        try {
            String password = "secr3t";
            ECKeyPair keyPair = Keys.createEcKeyPair();
            WalletFile wallet = Wallet.createStandard(password, keyPair);

            System.out.println("Priate key: " + keyPair.getPrivateKey().toString(16));
            System.out.println("Account: " + wallet.getAddress());

        } catch(Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

}
