package com.edunetcracker.simulator.service.routingService;


public class IpService {

    public static boolean isInSubnet (Integer ip, Integer subnetIp, Integer subnetMask) {
        //TODO: Mind problem with zero-mask!
        return  ((ip & subnetMask) == (subnetIp & subnetMask));
    }

    /**
     * Checks whether the combination of ip-mask can be a route.
     * @param ip
     * @param mask
     * @return
     */
    public static boolean isRouteLegal(int ip, int mask) {
        if (checkMaskBitSequence(mask) < 0) { return false; }
        return (ip & ~mask) == 0;
    }

    /**
     * Checks whether the mask has legal bit sequence (1..10..0)
     * @param mask mask in a form of 1..10..0
     * @return  Length of 'ones' sequence if the mask is legal, or '-1' if it is not.
     */
    public static int checkMaskBitSequence (int mask) {
        if (mask > 0xFFFFFFFF) return -1;
        int copy = mask;
        int lengthOfZeros = 0;
        while (copy % 2 != 0) {
            copy = copy>>1;
            lengthOfZeros++;
        }
        int lengthOfOnes = 32 - lengthOfZeros;
        return (mask == createMask(lengthOfOnes)) ? lengthOfOnes : -1;
    }

    /**
     * Creates a mask in a form of 1..10..0 out of a /24-like mask.
     * @param lengthOfOnes Mask representation "with a slash", a.k.a. a number of 'ones' in a mask.
     * @return 1..10..0 mask
     */
    public static int createMask (int lengthOfOnes) {
        int lengthOfZeros = 32-lengthOfOnes;
        return 0xFFFFFFFF << lengthOfZeros;
    }

    public static Integer intFromString (String strAddress) {
        int intAddress = 0;

        if (strAddress.matches("([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}")) {
            int[] ip = new int[4];
            String[] parts = strAddress.split("\\.");

            for (int i = 0; i < 4; i++) {
                intAddress += Integer.parseInt(parts[i]) << (24 - (8 * i));
            }
            return intAddress;
        }

        try {
            intAddress = Integer.parseInt(strAddress);
            return intAddress;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static String stringFromInt (Integer ip) {
        if (null == ip) {
            return null;
        }

        int byte0 = ip & 0xFF;
        int byte1 = (ip >> 8) & 0xFF;
        int byte2 = (ip >> 16) & 0xFF;
        int byte3 = (ip >> 24) & 0xFF;

        return String.format("%d.%d.%d.%d", byte3, byte2, byte1, byte0);
    }
}
