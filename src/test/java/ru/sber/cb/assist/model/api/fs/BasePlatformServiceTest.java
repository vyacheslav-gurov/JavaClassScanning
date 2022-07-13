package ru.sber.cb.assist.model.api.fs;

import api.PlatformServiceFactory;
import api.fs.FsPlatformServiceFactory;
import ru.sber.cb.assist.model.api.utils.PathUtils;

public class BasePlatformServiceTest {

    static PlatformServiceFactory platformServiceFactory = new FsPlatformServiceFactory(PathUtils.testResource("schemas").toString());

}
