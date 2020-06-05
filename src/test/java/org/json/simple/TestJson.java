/*
 * $Id: Test.java,v 1.1 2006/04/15 14:40:06 platform Exp $
 * Created on 2006-4-15
 */
package org.json.simple;

import org.json.simple.parser.ParseException;
import org.json.simple.parser.Yytoken;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestJson {

	@Test
	public void testDecode() throws Exception{
		System.out.println("=======decode=======");

		String s="[0,10]";
		Object obj=JSONValue.parse(s);
		JSONArray array=(JSONArray)obj;
		System.out.println("======the 2nd element of array======");
		System.out.println(array.get(1));
		System.out.println();
		assertEquals("10",array.get(1).toString());
	}

	@Test
	public void testJSONArrayCollection() {
		final ArrayList<String> testList = new ArrayList<String>();
		testList.add("First item");
		testList.add("Second item");
		final JSONArray jsonArray = new JSONArray(testList);

		assertEquals("[\"First item\",\"Second item\"]", jsonArray.toJSONString());
	}

	@Test
	public void testJSONObjectParse() {
		try {
			Object dictObj =  JSONValue.parseWithException("{\"anime\": [1,2,3]}");
			Object arrayObj = JSONValue.parseWithException("[1,2,3]");
			JSONObject jsonObject = (JSONObject) dictObj;
			JSONArray jsonArray = (JSONArray) arrayObj;
			assertEquals(((JSONArray) jsonObject.get("anime")).toJSONString(), jsonArray.toJSONString());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testYyTokenToString() {
		try {
			Object object =  JSONValue.parseWithException("[{},{\"one\":\"two\"}]");
			Yytoken token = new Yytoken(Yytoken.TYPE_VALUE, object);
			assertEquals(token.toString(), "VALUE([{},{\"one\":\"two\"}])");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testParseException() {
		boolean errorOccured = false;
		try {
			Object object = JSONValue.parseWithException("This is wrong");
		} catch (ParseException e) {
			errorOccured = true;
		}
		assertTrue(errorOccured);
	}
}
