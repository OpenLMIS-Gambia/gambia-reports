package org.openlmis.ao.reports.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import org.openlmis.ao.reports.exception.NotFoundMessageException;
import org.openlmis.ao.settings.domain.ConfigurationSetting;
import org.openlmis.ao.settings.exception.ConfigurationSettingException;
import org.openlmis.ao.settings.repository.ConfigurationSettingRepository;
import org.openlmis.ao.settings.service.ConfigurationSettingService;

@RunWith(MockitoJUnitRunner.class)
public class ConfigurationSettingServiceTest {

  @Mock
  private ConfigurationSettingRepository configurationSettingRepository;

  @InjectMocks
  private ConfigurationSettingService configurationSettingService;

  private ConfigurationSetting configurationSetting;

  @Before
  public void setUp() {
    generateInstances();
    mockRepositories();
  }

  @Test
  public void shouldGetConfigurationSettingByKeyIfKeyExists() throws ConfigurationSettingException {
    Assert.assertEquals(configurationSettingService.getByKey("key"), configurationSetting);
  }

  @Test(expected = NotFoundMessageException.class)
  public void shouldGetConfigurationSettingByKeyIfDoesNotKeyExists()
      throws NotFoundMessageException {
    configurationSettingService.getByKey("testEmpty");
  }

  @Test
  public void shouldGetValueIfKeyExists() throws ConfigurationSettingException {
    assertEquals(configurationSettingService.getStringValue("key"), "value");
  }

  @Test(expected = NotFoundMessageException.class)
  public void shouldThrowExceptionIfKeyDoesNotExists() throws NotFoundMessageException {
    configurationSettingService.getStringValue("testEmpty");
  }

  @Test
  public void shouldCatchExceptionAndReturnFalseIfKeyDoesNotExists()
      throws ConfigurationSettingException {
    assertEquals(configurationSettingService.getBoolValue("testEmpty"), Boolean.FALSE);
  }

  @Test
  public void shouldGetBoolTrueValueIfKeyExists() {
    configurationSetting = new ConfigurationSetting();
    configurationSetting.setKey("testTrue");
    configurationSetting.setValue(Boolean.TRUE.toString());
    when(configurationSettingRepository
        .findOne(configurationSetting.getKey()))
        .thenReturn(configurationSetting);
    assertEquals(configurationSettingService.getBoolValue("testTrue"), Boolean.TRUE);
  }

  @Test
  public void shouldGetBoolFalseValueIfKeyExists() {
    ConfigurationSetting setting = new ConfigurationSetting();
    setting.setKey("testFalse");
    setting.setValue(Boolean.FALSE.toString());
    when(configurationSettingRepository
        .findOne(configurationSetting.getKey()))
        .thenReturn(configurationSetting);
    assertEquals(configurationSettingService.getBoolValue("testFalse"), Boolean.FALSE);
  }

  private void generateInstances() {
    configurationSetting = new ConfigurationSetting();
    configurationSetting.setKey("key");
    configurationSetting.setValue("value");
  }

  private void mockRepositories() {
    when(configurationSettingRepository
        .findOne(configurationSetting.getKey()))
        .thenReturn(configurationSetting);
  }
}
