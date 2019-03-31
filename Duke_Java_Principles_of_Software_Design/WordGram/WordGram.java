import java.util.Arrays; 

public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        String ret = "";
        for (int k=0; k < myWords.length; k++) {
            ret += myWords[k] + " ";
        }
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if (this.length() != other.length()) {
            return false;
        }
        for (int k=0; k < myWords.length; k++) {
            if (!myWords[k].equals(other.wordAt(k))) {
                return false;
            }
        }
        return true;

    }

    public WordGram shiftAdd(String word) {	
       //System.out.println(this); 
       //System.out.println(Arrays.toString(myWords));
       WordGram out = new WordGram(myWords, 0, myWords.length);
       //System.out.println(out);
       String[] words = new String[out.length()];
        
       for(int i=0;i<words.length-1;i++){
           words[i] = out.wordAt(i+1);
       }
       words[words.length-1] = word;
       return new WordGram(words,0,words.length);
    }

}