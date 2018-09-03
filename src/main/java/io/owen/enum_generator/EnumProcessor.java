package io.owen.enum_generator;

import com.google.auto.service.AutoService;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by owen_q on 2018. 8. 31..
 */
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@AutoService(Processor.class)
public class EnumProcessor extends AbstractProcessor {
//    private Logger logger = LoggerFactory.getLogger(EnumProcessor.class);

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        List<IntermediateEnum> intermediateEnumList = this.parse(annotations, roundEnv);

        intermediateEnumList.stream()
                .map(intermediateEnum -> JSEnumWriter.create(intermediateEnum.getEnumStyle(), intermediateEnum))
                .map(jsEnumWriter -> jsEnumWriter.process())
                .forEach(aBoolean -> System.out.println(aBoolean));

        return true;
    }

    private List<IntermediateEnum> parse(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment){
//        if(logger.isInfoEnabled())
//            logger.info("Parsing @ToJs classes");

        System.out.println("Parsing @ToJS classes");

        List<IntermediateEnum> results = new ArrayList<>();

        for(TypeElement typeElement : annotations){
            for(Element element : roundEnvironment.getElementsAnnotatedWith(typeElement)) {
                System.out.println("Found @ToJS class");


                ToJS toJsElement = element.getAnnotation(ToJS.class);

                if(toJsElement != null) {
                    // Parse Class
                    String declaredClassName = element.getSimpleName().toString();

                    // Parse annotation properties
                    IntermediateEnum.Builder builder = new IntermediateEnum.Builder();
                    IntermediateEnum intermediateEnum = builder.defaultClassName(declaredClassName).givenClassName(toJsElement.name()).description(toJsElement.description()).build();

                    results.add(intermediateEnum);
                }

                System.out.println(element.getKind());

                if (element.getKind() == ElementKind.ENUM) {
                    // print fields
                    for (Element enclosedElement : element.getEnclosedElements()) {

                        System.out.println(enclosedElement.getKind());

                        if (enclosedElement.getKind() == ElementKind.ENUM_CONSTANT) {
                            Set<Modifier> modifiers = enclosedElement.getModifiers();
                            StringBuilder sb = new StringBuilder();
                            if (modifiers.contains(Modifier.PRIVATE)) {
                                sb.append("private ");
                            } else if (modifiers.contains(Modifier.PROTECTED)) {
                                sb.append("protected ");
                            } else if (modifiers.contains(Modifier.PUBLIC)) {
                                sb.append("public ");
                            }
                            if (modifiers.contains(Modifier.STATIC))
                                sb.append("static ");
                            if (modifiers.contains(Modifier.FINAL))
                                sb.append("final ");

                            sb.append(enclosedElement.asType()).append(" ").append(enclosedElement.getSimpleName());
                            System.out.println(sb);
                        }
                    }
                }
                else{
                    ;
                }

//                for(Field field : typeElement.getClass().getDeclaredFields()){
//                    System.out.println(field.getAnnotatedType().getType().getTypeName() + " : " + field.toString());
//                }

//                Stream.of(toJsElement.getClass().getDeclaredFields()).forEach(field -> System.out.println(field.getName()));
            }
        }

        return results;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> supportedAnnotationTypes = new HashSet<>();
        supportedAnnotationTypes.add("*");

        return supportedAnnotationTypes;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_8;
    }

}
