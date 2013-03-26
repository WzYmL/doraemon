package org.salever.osgi.console.extend;

public interface DictionaryService {
	
    /**
     * Register a dictionary
     * 
     * @param dictionary the dictionary to be added.
     */
    public void registerDictionary(Dictionary dictionary);
    
    /**
     * Remove a dictionary
     * 
     * @param dictionary the dictionary to be removed.
     */
    public void unregisterDictionary(Dictionary dictionary);
	
    /**
     * Check for the existence of a word across all dictionaries
     * 
     * @param word the word to be checked.
     * @return true if the word is in any dictionary
     */
    public boolean check(String word);
    
    /**
     * Return the list of languages supported
     * 
     * @return the list of languages in the dictionary
     */
    public String[] getLanguages();


}
