/*

    DISCLAIMER: I understand this is slow and janky, but I just needed something that worked.

*/

package com.skysucht.taeko.dupe;

public class LogProgress extends Thread {

    public void run() {

        while (true) {
            try {
                Thread.sleep(60*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("-----------------------");
            System.out.println("[Skysucht Dupe] - Stats");
            System.out.println("Searched Chunks: " + Dupe.searchedChunks);
            System.out.println("Of which have valuables: " + Dupe.chunksHaveValuables);
            System.out.println("-----------------------");
            Dupe.searchedChunks = 0; Dupe.chunksHaveValuables = 0;
        }

    }

}