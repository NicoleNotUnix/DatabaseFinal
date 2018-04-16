
public class Vocals {
	public static void synthesizeText(String text)
	{
		//TODO : This should be a singleton
		voce.SpeechInterface.init("../../../lib", true, false, "", "");
		voce.SpeechInterface.synthesize(text);
	}
}
