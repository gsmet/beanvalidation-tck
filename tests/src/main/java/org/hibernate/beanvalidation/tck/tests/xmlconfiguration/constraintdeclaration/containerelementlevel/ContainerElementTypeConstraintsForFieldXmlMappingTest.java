/**
 * Bean Validation TCK
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package org.hibernate.beanvalidation.tck.tests.xmlconfiguration.constraintdeclaration.containerelementlevel;

import static org.hibernate.beanvalidation.tck.util.ConstraintViolationAssert.assertThat;
import static org.hibernate.beanvalidation.tck.util.ConstraintViolationAssert.violationWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.beanvalidation.tck.beanvalidation.Sections;
import org.hibernate.beanvalidation.tck.tests.AbstractTCKTest;
import org.hibernate.beanvalidation.tck.util.CollectionHelper;
import org.hibernate.beanvalidation.tck.util.TestUtil;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.test.audit.annotations.SpecAssertion;
import org.jboss.test.audit.annotations.SpecVersion;
import org.testng.annotations.Test;

/**
 * @author Guillaume Smet
 */
@SpecVersion(spec = "beanvalidation", version = "2.0.0")
public class ContainerElementTypeConstraintsForFieldXmlMappingTest extends AbstractTCKTest {

	@Deployment
	public static WebArchive createTestArchive() {
		return webArchiveBuilder()
				.withTestClass( ContainerElementTypeConstraintsForFieldXmlMappingTest.class )
				.withResource( "field-canDeclareContainerElementTypeConstraints-mapping.xml" )
				.withResource( "field-canDeclareNestedContainerElementTypeConstraints-mapping.xml" )
				.withResource( "field-canDeclareDeeplyNestedContainerElementTypeConstraints-mapping.xml" )
				.withResource( "field-canDeclareContainerElementCascades-mapping.xml" )
				.withResource( "field-declaringContainerElementTypeConstraintOnNonGenericFieldCausesException-mapping.xml" )
				.withResource( "field-declaringContainerElementTypeConstraintForNonExistingTypeArgumentIndexOnFieldCausesException-mapping.xml" )
				.withResource( "field-declaringContainerElementTypeConstraintForNonExistingNestedTypeArgumentIndexOnFieldCausesException-mapping.xml" )
				.withResource( "field-omittingTypeArgumentForMultiTypeArgumentTypeOnFieldCausesException-mapping.xml" )
				.withResource( "field-configuringSameContainerElementTwiceCausesException-mapping.xml" )
				.build();
	}

	@Test
	@SpecAssertion(section = Sections.XML_MAPPING_CONSTRAINTDECLARATIONINXML_CONTAINERELEMENTCONSTRAINTS, id = "a")
	@SpecAssertion(section = Sections.XML_MAPPING_CONSTRAINTDECLARATIONINXML_CONTAINERELEMENTCONSTRAINTS, id = "b")
	@SpecAssertion(section = Sections.XML_MAPPING_CONSTRAINTDECLARATIONINXML_CONTAINERELEMENTCONSTRAINTS, id = "c")
	@SpecAssertion(section = Sections.XML_MAPPING_CONSTRAINTDECLARATIONINXML_CONTAINERELEMENTCONSTRAINTS, id = "d")
	@SpecAssertion(section = Sections.XML_MAPPING_CONSTRAINTDECLARATIONINXML_CONTAINERELEMENTCONSTRAINTS, id = "g")
	public void canDeclareContainerElementTypeConstraintsForFieldWithXmlMapping() {
		Validator validator = getValidator( "field-canDeclareContainerElementTypeConstraints-mapping.xml" );

		Set<ConstraintViolation<FishTank>> violations = validator.validate( new FishTank() );

		assertThat( violations ).containsOnlyViolations(
				violationWith()
						.constraintType( Size.class )
						.message( "size must be between 0 and 5" ),
				violationWith()
						.constraintType( Size.class )
						.message( "size must be between 3 and 10" ),
				violationWith()
						.constraintType( Size.class )
						.message( "size must be between 3 and 10" ),
				violationWith()
						.constraintType( Min.class )
						.message( "must be greater than or equal to 1" ),
				violationWith()
						.constraintType( Min.class )
						.message( "must be greater than or equal to 1" )
		);
	}

	@Test
	@SpecAssertion(section = Sections.XML_MAPPING_CONSTRAINTDECLARATIONINXML_CONTAINERELEMENTCONSTRAINTS, id = "a")
	@SpecAssertion(section = Sections.XML_MAPPING_CONSTRAINTDECLARATIONINXML_CONTAINERELEMENTCONSTRAINTS, id = "b")
	@SpecAssertion(section = Sections.XML_MAPPING_CONSTRAINTDECLARATIONINXML_CONTAINERELEMENTCONSTRAINTS, id = "c")
	@SpecAssertion(section = Sections.XML_MAPPING_CONSTRAINTDECLARATIONINXML_CONTAINERELEMENTCONSTRAINTS, id = "f")
	@SpecAssertion(section = Sections.XML_MAPPING_CONSTRAINTDECLARATIONINXML_CONTAINERELEMENTCONSTRAINTS, id = "g")
	public void canDeclareNestedContainerElementTypeConstraintsForFieldWithXmlMapping() {
		Validator validator = getValidator( "field-canDeclareNestedContainerElementTypeConstraints-mapping.xml" );

		Set<ConstraintViolation<FishTank>> violations = validator.validate( new FishTank() );

		assertThat( violations ).containsOnlyViolations(
				violationWith().constraintType( NotNull.class )
		);
	}

	@Test
	@SpecAssertion(section = Sections.XML_MAPPING_CONSTRAINTDECLARATIONINXML_CONTAINERELEMENTCONSTRAINTS, id = "a")
	@SpecAssertion(section = Sections.XML_MAPPING_CONSTRAINTDECLARATIONINXML_CONTAINERELEMENTCONSTRAINTS, id = "b")
	@SpecAssertion(section = Sections.XML_MAPPING_CONSTRAINTDECLARATIONINXML_CONTAINERELEMENTCONSTRAINTS, id = "c")
	@SpecAssertion(section = Sections.XML_MAPPING_CONSTRAINTDECLARATIONINXML_CONTAINERELEMENTCONSTRAINTS, id = "f")
	@SpecAssertion(section = Sections.XML_MAPPING_CONSTRAINTDECLARATIONINXML_CONTAINERELEMENTCONSTRAINTS, id = "g")
	public void canDeclareDeeplyNestedContainerElementTypeConstraintsForFieldWithXmlMapping() {
		Validator validator = getValidator( "field-canDeclareDeeplyNestedContainerElementTypeConstraints-mapping.xml" );

		Set<ConstraintViolation<FishTank>> violations = validator.validate( new FishTank() );

		assertThat( violations ).containsOnlyViolations(
				violationWith().constraintType( NotNull.class )
		);
	}

	@Test
	@SpecAssertion(section = Sections.XML_MAPPING_CONSTRAINTDECLARATIONINXML_CONTAINERELEMENTCONSTRAINTS, id = "a")
	@SpecAssertion(section = Sections.XML_MAPPING_CONSTRAINTDECLARATIONINXML_CONTAINERELEMENTCONSTRAINTS, id = "b")
	@SpecAssertion(section = Sections.XML_MAPPING_CONSTRAINTDECLARATIONINXML_CONTAINERELEMENTCONSTRAINTS, id = "c")
	@SpecAssertion(section = Sections.XML_MAPPING_CONSTRAINTDECLARATIONINXML_CONTAINERELEMENTCONSTRAINTS, id = "h")
	public void canDeclareContainerElementCascadesForFieldWithXmlMapping() {
		Validator validator = getValidator( "field-canDeclareContainerElementCascades-mapping.xml" );

		Set<ConstraintViolation<FishTank>> violations = validator.validate( new FishTank() );

		assertThat( violations ).containsOnlyViolations(
				violationWith().constraintType( NotNull.class )
		);
	}

	@Test(expectedExceptions = ValidationException.class)
	@SpecAssertion(section = Sections.XML_MAPPING_CONSTRAINTDECLARATIONINXML_CONTAINERELEMENTCONSTRAINTS, id = "j")
	public void declaringContainerElementTypeConstraintOnNonGenericFieldCausesException() {
		getValidator( "field-declaringContainerElementTypeConstraintOnNonGenericFieldCausesException-mapping.xml" );
	}

	@Test(expectedExceptions = ValidationException.class)
	@SpecAssertion(section = Sections.XML_MAPPING_CONSTRAINTDECLARATIONINXML_CONTAINERELEMENTCONSTRAINTS, id = "k")
	public void declaringContainerElementTypeConstraintForNonExistingTypeArgumentIndexOnFieldCausesException() {
		getValidator( "field-declaringContainerElementTypeConstraintForNonExistingTypeArgumentIndexOnFieldCausesException-mapping.xml" );
	}

	@Test(expectedExceptions = ValidationException.class)
	@SpecAssertion(section = Sections.XML_MAPPING_CONSTRAINTDECLARATIONINXML_CONTAINERELEMENTCONSTRAINTS, id = "k")
	public void declaringContainerElementTypeConstraintForNonExistingNestedTypeArgumentIndexOnFieldCausesException() {
		getValidator( "field-declaringContainerElementTypeConstraintForNonExistingNestedTypeArgumentIndexOnFieldCausesException-mapping.xml" );
	}

	@Test(expectedExceptions = ValidationException.class)
	@SpecAssertion(section = Sections.XML_MAPPING_CONSTRAINTDECLARATIONINXML_CONTAINERELEMENTCONSTRAINTS, id = "l")
	public void omittingTypeArgumentForMultiTypeArgumentTypeOnFieldCausesException() {
		getValidator( "field-omittingTypeArgumentForMultiTypeArgumentTypeOnFieldCausesException-mapping.xml" );
	}

	@Test(expectedExceptions = ValidationException.class)
	@SpecAssertion(section = Sections.XML_MAPPING_CONSTRAINTDECLARATIONINXML_CONTAINERELEMENTCONSTRAINTS, id = "m")
	public void configuringSameContainerElementTwiceCausesException() {
		getValidator( "field-configuringSameContainerElementTwiceCausesException-mapping.xml" );
	}

	private Validator getValidator(String mappingFile) {
		Configuration<?> config = TestUtil.getConfigurationUnderTest();
		config.addMapping( getClass().getResourceAsStream( mappingFile ) );
		return config.buildValidatorFactory().getValidator();
	}

	public static class FishTank {

		public Optional<String> model = Optional.of( "Too long" );
		public Optional<Fish> boss = Optional.of( new Fish() );
		public Map<String, Integer> fishCountByType;
		public Map<String, List<Fish>> fishOfTheMonth;
		public List<Map<String, Set<String>>> tagsOfFishOfTheMonth;
		public int size = 0;

		public FishTank() {
			fishCountByType = new HashMap<>();
			fishCountByType.put( "A", -1 );
			fishCountByType.put( "BB", -2 );

			fishOfTheMonth = new HashMap<>();
			List<Fish> january = Arrays.asList( null, new Fish() );
			fishOfTheMonth.put( "january", january );

			Set<String> bobsTags = CollectionHelper.asSet( (String) null );
			Map<String, Set<String>> januaryTags = new HashMap<>();
			januaryTags.put( "bob", bobsTags );
			tagsOfFishOfTheMonth = new ArrayList<>();
			tagsOfFishOfTheMonth.add( januaryTags );
		}
	}

	public static class Fish {

		public Fish() {
		}

		public String name;
	}
}
