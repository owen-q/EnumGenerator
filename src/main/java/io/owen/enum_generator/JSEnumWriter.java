package io.owen.enum_generator;

import io.owen.enum_generator.enums.EnumStyle;

/**
 * Created by owen_q on 2018. 8. 31..
 */
public class JSEnumWriter {
//    private Logger logger = LoggerFactory.getLogger(JSEnumWriter.class);

    private IntermediateEnum intermediateEnum = null;

    // Write Generated Enums to Javascript file
    private JSEnumWriter(){
    }

    private JSEnumWriter(IntermediateEnum intermediateEnum) {
        this.intermediateEnum = intermediateEnum;
    }

    public static JSEnumWriter create(EnumStyle enumStyle, IntermediateEnum intermediateEnum){
        return new JSEnumWriter(intermediateEnum);
    }

    private String format = "";

    public boolean process(){
//        if(logger.isInfoEnabled())
//            logger.info("Process: " + this.intermediateEnum);
        System.out.println("Process: " + this.intermediateEnum);

        return true;
    }

    private String generateFullJSEnumClass(){
        return "";
    }

    private boolean export(){
        return true;
    }
}

/*

-- 1)
const Colors = Object.freeze({
    RED:   Symbol("red"),
    BLUE:  Symbol("blue"),
    GREEN: Symbol("green")
});



-- 2)

var DaysEnum = {"monday":1, "tuesday":2, "wednesday":3, ...}
Object.freeze(DaysEnum)


-- 3)

var DaysEnum = Object.freeze({"monday":1, "tuesday":2, "wednesday":3, ...})


 */