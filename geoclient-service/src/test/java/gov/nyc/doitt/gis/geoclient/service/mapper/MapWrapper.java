package gov.nyc.doitt.gis.geoclient.service.mapper;

import java.util.HashMap;
import java.util.Map;

import gov.nyc.doitt.gis.geoclient.api.OutputParam;

public class MapWrapper {
    private final Map<String, Object> map;

    public MapWrapper(Map<String, Object> map) {
        super();
        this.map = map;
    }

    public MapWrapper() {
        this(new HashMap<String, Object>());
    }

    public void reset() {
        this.map.clear();
    }

    public Map<String, Object> getMap() {
        return this.map;
    }

    public String getReturnCode1() {
        return stringOrNull(OutputParam.GEOSUPPORT_RETURN_CODE);
    }

    public String getReturnCode2() {
        return stringOrNull(OutputParam.GEOSUPPORT_RETURN_CODE2);
    }

    public String getReasonCode1() {
        return stringOrNull(OutputParam.REASON_CODE);
    }

    public String getReasonCode2() {
        return stringOrNull(OutputParam.REASON_CODE2);
    }

    public String getMessage1() {
        return stringOrNull(OutputParam.MESSAGE);
    }

    public String getMessage2() {
        return stringOrNull(OutputParam.MESSAGE2);
    }

    public MapWrapper setReturnCode1(String value) {
        this.map.put(OutputParam.GEOSUPPORT_RETURN_CODE, value);
        return this;
    }

    public MapWrapper setReturnCode2(String value) {
        this.map.put(OutputParam.GEOSUPPORT_RETURN_CODE2, value);
        return this;
    }

    public MapWrapper setReasonCode1(String value) {
        this.map.put(OutputParam.REASON_CODE, value);
        return this;
    }

    public MapWrapper setReasonCode2(String value) {
        this.map.put(OutputParam.REASON_CODE2, value);
        return this;
    }

    public MapWrapper setMessage1(String value) {
        this.map.put(OutputParam.MESSAGE, value);
        return this;
    }

    public MapWrapper setMessage2(String value) {
        this.map.put(OutputParam.MESSAGE2, value);
        return this;
    }

    private String stringOrNull(String key) {
        if (this.map.containsKey(key)) {
            return this.map.get(key).toString();
        }
        return null;
    }

    public static MapWrapper successWithWarning() {
        ReturnCodeData rdc = new ReturnCodeData();
        // @formatter:off
        return MapWrapper.wrapMap(
                rdc.success().returnCode()
                   .then().success().reasonCode()
                   .then().success().message()
                   .then().warning().returnCode()
                   .then().warning().reasonCode()
                   .then().warning().message()
                   .values());
        // @formatter:on
    }

    public static MapWrapper warningWithSuccess() {
        ReturnCodeData rdc = new ReturnCodeData();
        // @formatter:off
        return MapWrapper.wrapMap(
                rdc.warning().returnCode()
                   .then().warning().reasonCode()
                   .then().warning().message()
                   .then().success().returnCode()
                   .then().success().reasonCode()
                   .then().success().message()
                   .then().values());
        // @formatter:on
        // return MapWrapper.wrapMap(rdc.WARNING_RETURN_CODE, rdc.WARNING_REASON_CODE,
        // rdc.WARNING_MESSAGE,
        // rdc.SUCCESS_RETURN_CODE, rdc.SUCCESS_REASON_CODE, rdc.SUCCESS_MESSAGE);
    }

    // Assumes one to six arguments in the following order:
    // String returnCode1, reasonCode1, message1, returnCode2, reasonCode2, message2
    public static MapWrapper wrapMap(String... strings) {
        if (strings == null || strings.length == 0) {
            throw new NullPointerException("This function requires 1 to 6 string arguments but none were given");
        }
        if (strings.length < 1 || strings.length > 6) {
            throw new NullPointerException(String
                    .format("This function requires 1 to 6 string arguments but %d were given", strings.length));
        }
        MapWrapper result = new MapWrapper(new HashMap<>());
        for (int i = 0; i < strings.length; i++) {
            String string = strings[i];
            int arg = i + 1;
            switch (arg) {
            case 1:
                result.setReturnCode1(string);
                break;
            case 2:
                result.setReasonCode1(string);
                break;
            case 3:
                result.setMessage1(string);
                break;
            case 4:
                result.setReturnCode2(string);
                break;
            case 5:
                result.setReasonCode2(string);
                break;
            case 6:
                result.setMessage2(string);
                break;

            default:
                throw new IllegalStateException();
            }
        }
        return result;
    }
}