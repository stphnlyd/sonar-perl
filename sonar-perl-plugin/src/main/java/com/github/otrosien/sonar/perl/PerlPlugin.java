package com.github.otrosien.sonar.perl;

import java.util.Arrays;

import org.sonar.api.Plugin;
import org.sonar.api.Properties;
import org.sonar.api.Property;

import com.github.otrosien.sonar.perl.colorizer.PerlCodeColorizer;
import com.github.otrosien.sonar.perl.critic.PerlCriticProperties;
import com.github.otrosien.sonar.perl.critic.PerlCriticIssuesLoaderSensor;
import com.github.otrosien.sonar.perl.critic.SonarWayProfile;
import com.github.otrosien.sonar.perl.critic.PerlCriticRulesDefinition;
import com.github.otrosien.sonar.perl.tap.TestHarnessArchiveProperties;
import com.github.otrosien.sonar.perl.tap.TestHarnessLoaderSensor;

@Properties({ //
    @Property( //
    key = PerlPlugin.FILE_SUFFIXES_KEY, //
    name = "File Suffixes", //
    description = "Comma-separated list of suffixes for files to analyze.", //
    defaultValue = PerlPlugin.DEFAULT_FILE_SUFFIXES), //
    @Property( //
    key = PerlCriticProperties.PERLCRITIC_REPORT_PATH_KEY, //
    name = "PerlCritic Report Location", //
    description = "Location of perlcritic report file. Can be generated using this command-line: perlcritic --quiet --verbose \"%f~|~%s~|~%l~|~%c~|~%m~|~%e~|~%p~||~%n\"", //
    defaultValue = PerlCriticProperties.PERLCRITIC_REPORT_PATH_DEFAULT ), //
    @Property( //
    key = TestHarnessArchiveProperties.HARNESS_ARCHIVE_PATH_KEY, //
    name = "Test::Harness Archive Location", //
    description = "Location of Test::Harness::Archive report file. Can be generated using this command-line: prove -t -a testReport.tgz", //
    defaultValue = TestHarnessArchiveProperties.HARNESS_ARCHIVE_PATH_DEFAULT ) //
})
public class PerlPlugin implements Plugin {

    public static final String FILE_SUFFIXES_KEY = "com.github.otrosien.sonar.perl.suffixes";

    public static final String DEFAULT_FILE_SUFFIXES = "pm,pl,t";

    @Override
    public void define(Context context) {
        context.addExtensions(Arrays.asList(
                PerlLanguage.class,
                PerlCriticRulesDefinition.class, 
                SonarWayProfile.class,
                PerlCodeColorizer.class,
                GlobalSensor.class,
                PerlCriticIssuesLoaderSensor.class,
                TestHarnessLoaderSensor.class
        ));
    }

}
