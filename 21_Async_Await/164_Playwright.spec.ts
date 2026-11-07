import { test, expect, type Page } from '@playwright/test';

test('uses await in a Playwright test', async ({ page }: { page: Page }) => {
    await page.goto('https://playwright.dev/');
   //  page.locator();

});