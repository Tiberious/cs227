/*****************************************************************************
 *  Compilation:  javac GuitarHeroLite.java
 *  Execution:    java  GuitarHeroLite
 *  Dependencies: StdAudio.java StdDraw.java GuitarString.java
 *
 *  when the user types one of the characters from keyboard then
 *  a concert pitch will play.
 *
 ****************************************************************************/

public class GuitarHero{

    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString [] notes =  new GuitarString [keyboard.length()];
        int arryL = keyboard.length();
        
        for (int i=0; i < arryL; i++){
           double freq = 440 * Math.pow(2, (i-24.0) / 12.0);
           notes[i] = new GuitarString(freq);
        }
        
        // the main input loop
        while (true) {

            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {
                // the user types this character
                char key = StdDraw.nextKeyTyped();

                // pluck the corresponding string
                if (keyboard.indexOf(key) != -1) { 
                    int letter = keyboard.indexOf(key); 
                    notes[letter].pluck(); 
                }
                
                // if key is pressed that is not within the keyboard output error
                else System.out.println("The key you pushed is invalid");
            } // end of if statement
            
            // compute the superposition of the samples
            double sample = 0;
            for (int i=0; i < arryL; i++){
                sample += notes[i].sample();    
            }
            
            // send the result to standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            for (int i=0; i < arryL; i++){
                notes[i].tic(); 
            }

        } // end of while loop
    } // end of MAIN
} // end of Class