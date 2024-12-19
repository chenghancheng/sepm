import pluginVue from 'eslint-plugin-vue'
import prettier from 'eslint-config-prettier'
import tseslint from 'typescript-eslint'
import vueEslintParser from 'vue-eslint-parser'
import { parser } from 'typescript-eslint'

export default [
 ...pluginVue.configs['flat/recommended'],
 prettier,
 ...tseslint.configs['recommended'],
  {
    languageOptions: {
      parser: vueEslintParser, 
      parserOptions: {
        parser: parser,
        ecmaVersion: 2020,
        sourceType: 'module'
      }
    },
    rules:{
      "typescript-eslint/no-unused-vars": "warn",
    }
  }
]