package crypto.messages.request;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import crypto.messages.ClientMessageType;

public class SpecsDoneRequest extends RequestBase {
	
	public SpecsDoneRequest() {
		setType(ClientMessageType.CLIENT_SPEC_DONE);
	}
	
	public SpecsDoneRequest(int n) {
		setType(ClientMessageType.CLIENT_SPEC_DONE);
		setCounter(n);
	}

	@SuppressWarnings("unchecked")
	@Override
	public String toJSON() {
		JSONObject object = new JSONObject();
		object.put("type", getType().toString());
		object.put("n", getCounter());
		return object.toJSONString();
	}

	@Override
	public void fromJSON(String json) throws ParseException {
		JSONObject object = (JSONObject)parser.parse(json);
		setCounter((long)object.get("n"));
	}

}
