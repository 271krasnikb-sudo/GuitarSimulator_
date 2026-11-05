public class GuitarSimulator
{
    public static void main(String[] args)
    {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] strings = new GuitarString[keyboard.length()];

        for (int i = 0; i < strings.length; i++) {
            double frequency = 440.0 * Math.pow(1.05956, i - 24);
            strings[i] = new GuitarString(frequency);
        }

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int stringIndex = keyboard.indexOf(key);
                if (stringIndex >= 0) {
                    strings[stringIndex].pluck();
                }
            }

            double sample = 0.0;
            for (GuitarString string : strings) {
                sample += string.sample();
            }
            StdAudio.play(sample);

            for (GuitarString string : strings) {
                string.tic();
            }
        }
    }
}
