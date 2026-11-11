async function getExmaple(){
    return "abc";
}


import { test, type Page } from '@playwright/test';
test('user say hello', async ({ page }: { page: Page })=>{
            let t=  await page.goto("");
            let c = await getExmaple();
});