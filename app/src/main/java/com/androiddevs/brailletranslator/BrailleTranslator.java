package com.androiddevs.brailletranslator;

import java.util.Map;
import java.lang.String;
import java.util.HashMap;
import java.lang.StringBuilder;

public class BrailleTranslator
{
	private String Text;
	private static final Map<Character, String> BrailleDots = new HashMap<>();
	private static final Map<String, Character> ReversedBrailleDots = new HashMap<>();

	static {
		BrailleDots.put('a', "⠁");
        BrailleDots.put('b', "⠃");
        BrailleDots.put('c', "⠉");
        BrailleDots.put('d', "⠙");
        BrailleDots.put('e', "⠑");
        BrailleDots.put('f', "⠋");
        BrailleDots.put('g', "⠛");
        BrailleDots.put('h', "⠓");
        BrailleDots.put('i', "⠊");
        BrailleDots.put('j', "⠚");
        BrailleDots.put('k', "⠅");
        BrailleDots.put('l', "⠇");
        BrailleDots.put('m', "⠍");
        BrailleDots.put('n', "⠝");
        BrailleDots.put('o', "⠕");
        BrailleDots.put('p', "⠏");
        BrailleDots.put('q', "⠟");
        BrailleDots.put('r', "⠗");
        BrailleDots.put('s', "⠎");
        BrailleDots.put('t', "⠞");
        BrailleDots.put('u', "⠥");
        BrailleDots.put('v', "⠧");
        BrailleDots.put('w', "⠺");
        BrailleDots.put('x', "⠭");
        BrailleDots.put('y', "⠽");
        BrailleDots.put('z', "⠵");
        BrailleDots.put('1', "⠼⠁");
        BrailleDots.put('2', "⠼⠃");
        BrailleDots.put('3', "⠼⠉");
        BrailleDots.put('4', "⠼⠙");
        BrailleDots.put('5', "⠼⠑");
        BrailleDots.put('6', "⠼⠋");
        BrailleDots.put('7', "⠼⠛");
        BrailleDots.put('8', "⠼⠓");
        BrailleDots.put('9', "⠼⠊");
        BrailleDots.put('0', "⠼⠚");
        BrailleDots.put(',', "⠂");
        BrailleDots.put(';', "⠆");
        BrailleDots.put(':', "⠒");
        BrailleDots.put('.', "⠲");
        BrailleDots.put('!', "⠖");
        BrailleDots.put('(', "⠐⠣");
        BrailleDots.put(')', "⠐⠜");
        BrailleDots.put('?', "⠦");
        BrailleDots.put('\'', "⠸⠡");
        BrailleDots.put('-', "⠤");
        BrailleDots.put('/', "⠸⠌");
        BrailleDots.put('@', "⠈");
        BrailleDots.put('+', "⠖");
        BrailleDots.put('*', "⠔");
        BrailleDots.put('=', "⠶");
        BrailleDots.put('$', "⠎");
        BrailleDots.put('&', "⠯");
        BrailleDots.put('%', "⠴");
        BrailleDots.put('\"', "⠐");
        BrailleDots.put('#', "⠼");
		BrailleDots.put('_', "⠸");
	}

	static {
		BrailleDots.put('a', "⠁");
        BrailleDots.put('b', "⠃");
        BrailleDots.put('c', "⠉");
        BrailleDots.put('d', "⠙");
        BrailleDots.put('e', "⠑");
        BrailleDots.put('f', "⠋");
        BrailleDots.put('g', "⠛");
        BrailleDots.put('h', "⠓");
        BrailleDots.put('i', "⠊");
        BrailleDots.put('j', "⠚");
        BrailleDots.put('k', "⠅");
        BrailleDots.put('l', "⠇");
        BrailleDots.put('m', "⠍");
        BrailleDots.put('n', "⠝");
        BrailleDots.put('o', "⠕");
        BrailleDots.put('p', "⠏");
        BrailleDots.put('q', "⠟");
        BrailleDots.put('r', "⠗");
        BrailleDots.put('s', "⠎");
        BrailleDots.put('t', "⠞");
        BrailleDots.put('u', "⠥");
        BrailleDots.put('v', "⠧");
        BrailleDots.put('w', "⠺");
        BrailleDots.put('x', "⠭");
        BrailleDots.put('y', "⠽");
        BrailleDots.put('z', "⠵");
        BrailleDots.put('1', "⠼⠁");
        BrailleDots.put('2', "⠼⠃");
        BrailleDots.put('3', "⠼⠉");
        BrailleDots.put('4', "⠼⠙");
        BrailleDots.put('5', "⠼⠑");
        BrailleDots.put('6', "⠼⠋");
        BrailleDots.put('7', "⠼⠛");
        BrailleDots.put('8', "⠼⠓");
        BrailleDots.put('9', "⠼⠊");
        BrailleDots.put('0', "⠼⠚");
        BrailleDots.put(',', "⠂");
        BrailleDots.put(';', "⠆");
        BrailleDots.put(':', "⠒");
        BrailleDots.put('.', "⠲");
        BrailleDots.put('!', "⠖");
        BrailleDots.put('(', "⠦");
        BrailleDots.put(')', "⠴");
        BrailleDots.put('?', "⠦");
        BrailleDots.put('\'', "⠄");
        BrailleDots.put('-', "⠤");
        BrailleDots.put('/', "⠌");
        BrailleDots.put('@', "⠈");
        BrailleDots.put('+', "⠖");
        BrailleDots.put('*', "⠔");
        BrailleDots.put('=', "⠶");
        BrailleDots.put('$', "⠎");
        BrailleDots.put('&', "⠯");
        BrailleDots.put('%', "⠴");
        BrailleDots.put('\"', "⠐");
        BrailleDots.put('#', "⠼");
		BrailleDots.put('_', "⠸");

		for (Map.Entry<Character, String> BrailleDotsEntry : BrailleDots.entrySet())
		{
            ReversedBrailleDots.put(BrailleDotsEntry.getValue(), BrailleDotsEntry.getKey());
        }
	}

	public BrailleTranslator(String Text)
	{
		this.Text = Text.toLowerCase();
	}

	public String translateToBrailleDots()
	{
		StringBuilder stringbuilder = new StringBuilder();
		for (char character : this.Text.toCharArray())
		{
			String BrailleDot = BrailleDots.getOrDefault(character, String.valueOf(character));
			stringbuilder.append(BrailleDot);
		}
		String TranslatedBrailleDots = stringbuilder.toString();
		return TranslatedBrailleDots;
	}

	public String translateToText()
	{
		StringBuilder stringbuilder = new StringBuilder();
        for (int i = 0; i < this.Text.length(); i++)
		{
            String BrailleCharacter = String.valueOf(this.Text.charAt(i));
            Character TextCharacter = ReversedBrailleDots.get(BrailleCharacter);
            if (TextCharacter != null)
			{
                stringbuilder.append(TextCharacter);
            }
			else
			{
                stringbuilder.append(BrailleCharacter);
            }
        }
		String TranslatedText = stringbuilder.toString();
        return TranslatedText;
	}
}
