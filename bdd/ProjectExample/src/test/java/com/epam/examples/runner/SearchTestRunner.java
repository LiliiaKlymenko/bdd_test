package com.epam.examples.runner;

import static org.jbehave.core.io.CodeLocations.codeLocationFromPath;
import com.epam.examples.steps.SearchSteps;
import com.epam.tests.pages.PageFactory;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.embedder.executors.SameThreadExecutors;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.web.selenium.FirefoxWebDriverProvider;
import org.jbehave.web.selenium.PerStoriesWebDriverSteps;
import org.jbehave.web.selenium.PerStoryWebDriverSteps;
import org.jbehave.web.selenium.WebDriverProvider;
import org.jbehave.web.selenium.WebDriverScreenshotOnFailure;
import org.jbehave.web.selenium.WebDriverSteps;
import org.jbehave.web.selenium.PropertyWebDriverProvider;
import java.util.List;

public class SearchTestRunner extends JUnitStories {

    public static final String STORIES_RELATIVE_DIRECTORY = "src/test/resources";
    public static final String REPORT_RELATIVE_DIRECTORY = "../build/reports/jbehave";
    public static final String STORY_TO_EXCLUDE = "**/exclude_*.story";

    private WebDriverProvider driverProvider = new PropertyWebDriverProvider();
    private WebDriverSteps lifecycleSteps = new PerStoriesWebDriverSteps(driverProvider);
    private PageFactory pageFactory = new PageFactory(driverProvider);

    public SearchTestRunner() {
        if (lifecycleSteps instanceof PerStoriesWebDriverSteps) {
            configuredEmbedder().useExecutorService(new SameThreadExecutors().create(
                configuredEmbedder().embedderControls()));
        }
        configuredEmbedder().embedderControls().useStoryTimeoutInSecs(
            1000);
    }
    
    static{
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
    }
   
    @Override
    public Configuration configuration() {
        return new MostUsefulConfiguration().useStoryLoader(
                new LoadFromClasspath(getClass().getClassLoader()))
                .useStoryReporterBuilder(
                    new StoryReporterBuilder()
                        .withFormats(Format.XML, Format.STATS, Format.CONSOLE,
                            Format.HTML));
    }

    public InstanceStepsFactory stepsFactory() {
        Configuration configuration = configuration();
        return new InstanceStepsFactory(configuration, new SearchSteps(
            pageFactory), lifecycleSteps, new WebDriverScreenshotOnFailure(
            driverProvider, configuration.storyReporterBuilder()));
    }

    @Override
    protected List<String> storyPaths() {
        String storyToInclude = "**/" + System.getProperty("story", "*")
            + ".story";
        return new StoryFinder().findPaths(
            codeLocationFromPath(STORIES_RELATIVE_DIRECTORY),
            storyToInclude, STORY_TO_EXCLUDE);
    }

}
