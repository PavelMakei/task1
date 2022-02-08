package by.makei.tariff.factory;

import by.makei.tariff.builder.DomTariffBuilder;
import by.makei.tariff.builder.TariffBuilder;
import by.makei.tariff.exception.CustomException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TariffBuilderFactory {
    private final Logger logger = LogManager.getLogger();
    public static final TariffBuilderFactory instance = new TariffBuilderFactory();

    private enum TypeParser {
        DOM, SAX, STAX, JAXB
    }

    private TariffBuilderFactory(){}

    public static TariffBuilderFactory getInstance(){
        return instance;
    }

    public  TariffBuilder createBuilder(String tariffBuilderTypeName) throws CustomException {
        if(tariffBuilderTypeName==null || !tariffBuilderTypeName.matches("^\\w{1,4}$")){
            logger.log(Level.ERROR,"tariffBuilderTypeName is null or invalid");
            throw new CustomException("tariffBuilderTypeName is null or invalid");
        }
        TypeParser type = TypeParser.valueOf(tariffBuilderTypeName.toUpperCase());
        switch (type) {
            case DOM -> {
                return new DomTariffBuilder();
            }
            case SAX -> {
                return null;//new SaxTariffBuilder();
            }
            case STAX -> {
                return null;//new StaxTariffBuilder();
            }
            case JAXB -> {
                return null;//new StaxTariffBuilder();
            }

            default -> {
                logger.log(Level.ERROR,"Wrong builder type: " + type);
                throw new CustomException("Wrong builder type: " + type);
            }
        }
    }
}
