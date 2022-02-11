package by.makei.tariff.factory;

import by.makei.tariff.builder.DomTariffBuilder;
import by.makei.tariff.builder.SaxTariffBuilder;
import by.makei.tariff.builder.TariffBuilder;
import by.makei.tariff.exception.CustomException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TariffBuilderFactory {
    private static final Logger logger = LogManager.getLogger();
    public static final TariffBuilderFactory instance = new TariffBuilderFactory();
    private static final String REGEXP_VALIDATOR = "^\\w{3,4}$";

    private static final String DOM = "DOM";
    private static final String SAX = "SAX";
    private static final String STAX = "STAX";
    private static final String JAXB = "JAXB";


    private TariffBuilderFactory(){}

    public static TariffBuilderFactory getInstance(){
        return instance;
    }

    public  TariffBuilder createBuilder(String tariffBuilderTypeName) throws CustomException {
        if(tariffBuilderTypeName==null || !tariffBuilderTypeName.matches(REGEXP_VALIDATOR)){
            logger.log(Level.ERROR,"tariffBuilderTypeName is null or invalid");
            throw new CustomException("tariffBuilderTypeName is null or invalid");
        }
        String type = (tariffBuilderTypeName.toUpperCase());
        switch (type) {
            case DOM -> {
                return new DomTariffBuilder();
            }
            case SAX -> {
                return new SaxTariffBuilder();
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
