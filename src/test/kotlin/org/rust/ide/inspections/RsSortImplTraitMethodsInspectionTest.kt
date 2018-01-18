/*
 * Use of this source code is governed by the MIT license that can be
 * found in the LICENSE file.
 */

package org.rust.ide.inspections

class RsSortImplTraitMethodsInspectionTest : RsInspectionsTestBase(RsSortImplTraitMethodsInspection()) {

    fun `test different order`() = checkByText("""
        struct Struct {
            i: i32
        }

        trait Trait {
            fn test1(&self) -> i32;
            fn test2(&self) -> i32;
            fn test3(&self) -> i32;
        }

        <weak_warning descr="Different impl methods order from the trait">impl Trait for Struct {
            fn test3(&self) -> i32 {
                self.i * 3
            }
            fn test1(&self) -> i32 {
                self.i
            }
            fn test2(&self) -> i32 {
                self.i * 2
            }
        }</weak_warning>
    """)

    fun `test same order`() = checkByText("""
        struct Struct {
            i: i32
        }

        trait Trait {
            fn test1(&self) -> i32;
            fn test2(&self) -> i32;
            fn test3(&self) -> i32;
        }

        /*caret*/impl Trait for Struct {
            fn test1(&self) -> i32 {
                self.i
            }
            fn test2(&self) -> i32 {
                self.i * 2
            }
            fn test3(&self) -> i32 {
                self.i * 3
            }
        }
    """)


}

