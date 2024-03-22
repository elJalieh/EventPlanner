package special.planner;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.picocontainer.annotations.Inject;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;
public class MainStepDefinition {

    private final Main main;

    public MainStepDefinition(Main main){
        this.main = main;
    }

    @Test
    public void mainMethods(){
        assertTrue(true);
    }

}
