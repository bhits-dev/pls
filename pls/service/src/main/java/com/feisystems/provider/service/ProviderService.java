package com.feisystems.provider.service;

import java.util.Map;

import com.feisystems.provider.service.dto.ProviderDto;

/**
 * Contract for service to retrieve HIPPA providers
 *
 */

public interface ProviderService {
	public static final String SEARCH_STRING = "%";

/**
	 * Get a single {@link ccom.feisystems.provider.ProviderDtobased on National Provider Id
	 * @param npi unique National Provider Id
	 * @return {@link cocom.feisystems.provider.ProviderDto	 */
	public ProviderDto getProvider(String npi);

/**
	 * Retrieve {@link com.com.feisystems.provider.ProviderDtousing the
	 * parameters described below. Since the postal code may be wildcarded, use
	 * this method to do searches not pertaining to locaion by setting
	 *
	 * @code postalCode} to "%".
	 *
	 * @param genderCode
	 *            implementation should understand <b>m/f</b> and
	 *            <b>male/female</b> and may be wildcard to '%'. May or may not
	 *            be case sensitive.
	 * @param postalCode
	 *            wildcard searchable if contains less than 9 characters
	 *            implementation should add '%' at the end for search i.e.
	 *            '12345%' May be wildcard to '%'
	 * @param taxonomy
	 *            wildcard searchable speciality so implementaion should add '%'
	 *            at the end for search i.e. 'general%' May be wildcard to '%'
	 * @param phone
	 *            phone number no partial wildcard but may be fully wildcard to
	 *            '%'
	 * @param lastName
	 *            no partial wildcard but may be fully wildcard to '%'
	 * @param firstName
	 *            no partial wildcard but may be fully wildcard to '%'
	 * @param entityType
	 *            wildcard searchable speciality so implementation should add
	 *            '%' at the end for search i.e. 'general%' May be wildcard to
	 *            '%'
	 * @param providerOrganizationName
	 *            wildcard searchable speciality so implementation should add
	 *            '%' at the end for search i.e. 'general%' May be wildcard to
	 *            '%'
	 * @param pageNumber
	 *            page number to display
	 * @return {@code java.util.List} of
	 *         {@link com.feisystems.provider.domain.service.dto.fcom.feisystems.provider.ProviderDto
	 */
	Map<String, Object> getByGenderCodeAndPostalCodeAndSpecialityAndTelephoneNumberAndLastNameAndFirstNameAndEntityTypeAndProviderOrganizationName(
			String genderCode, String postalCode, String taxonomy,
			String phone, String lastName, String firstName, String entityType,
			String providerOrganizationName, String pageNumber);

	Map<String, Object> getByGenderCodeAndPostalCodeAndSpecialityAndTelephoneNumberAndLastNameOrProviderOrganizationNameAndFirstNameAndEntityType(
			String genderCode, String postalCode, String taxonomy,
			String phone, String lastNameOrFacilityName, String firstName,
			String entityType, String pageNumber);

	/**
	 * Retrieve {@link com.feicom.feisystems.provider.ProviderDtong the
	 * parameters described below. In this case, {@code usStateAbbreviation} and
	 * {@code city} together represent a compound key when seaarching so "%" for
	 * {@code city} and {@code usStateAbbreviation} are not guarenteed to return
	 * accurate results.
	 *
	 * @param genderCode
	 *            implementation should understand <b>m/f</b> and
	 *            <b>male/female</b> and may be wildcard to '%'. May or may not
	 *            be case sensitive.
	 * @param usStateAbbreviation
	 *            no wildcard
	 * @param city
	 *            city name no wildcard
	 * @param taxonomy
	 *            wildcard searchable speciality so implementaion should add '%'
	 *            at the end for search i.e. 'general%' May be wildcard to '%'
	 * @param phone
	 *            phone number no partial wildcard but may be fully wildcard to
	 *            '%'
	 * @param lastName
	 *            no partial wildcard but may be fully wildcard to '%'
	 * @param firstName
	 *            no partial wildcard but may be fully wildcard to '%'
	 * @param entityType
	 *            wildcard searchable speciality so implementation should add
	 *            '%' at the end for search i.e. 'general%' May be wildcard to
	 *            '%'
	 * @param providerOrganizationName
	 *            wildcard searchable speciality so implementation should add
	 *            '%' at the end for search i.e. 'general%' May be wildcard to
	 *            '%
	 * @param pageNumber
	 *            page number to display
	 * @return {@code java.util.List} of
	 *         {@link com.feisystems.provider.domain.service.dto.feiscom.feisystems.provider.ProviderDto
	 */
	public Map<String, Object> getByGenderCodeAndUSStateAbbreviationAndCityAndSpecialityAndTelephoneNumberAndLastNameAndFirstNameAndEntityTypeAndProviderOrganizationName(
			String genderCode, String usStateAbbreviation, String city,
			String taxonomy, String phone, String lastName, String firstName,
			String entityType, String providerOrganizationName,
			String pageNumber);

	public Map<String, Object> getByGenderCodeAndUSStateAbbreviationAndCityAndSpecialityAndTelephoneNumberAndLastNameOrProviderOrganizationNameAndFirstNameAndEntityType(
			String genderCode, String usStateAbbreviation, String city,
			String taxonomy, String phone, String lastNameOrFacilityName,
			String firstName, String entityType, String pageNumber);

}
