
public class Vocals {
	public static void synthesizeText(String text)
	{
		voce.SpeechInterface.init("../../../lib", true, false, "", "");
		voce.SpeechInterface.synthesize(text);
	}
}
