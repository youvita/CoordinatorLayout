# CoordinatorLayout

1. In build gradle add:
implementation 'com.android.support:design:28.0.0'

2. Sample xml layout:
<android.support.design.widget.CoordinatorLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_below="@+id/tool_bar"
        android:background="#ffff">

        <!-- everything will hide in this block -->
        <android.support.design.widget.AppBarLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:background="#ffff">

            <android.support.design.widget.CollapsingToolbarLayout
                android:id="@+id/collapsing_toolbar"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                app:layout_collapseMode="pin"
                app:layout_scrollFlags="scroll|exitUntilCollapsed">

                <RelativeLayout
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    app:layout_collapseMode="parallax">

                    <!-- your view need to hide will be here -->

                </RelativeLayout>

            </android.support.design.widget.CollapsingToolbarLayout>

        </android.support.design.widget.AppBarLayout>

        <!-- scroll view in this block -->
        <android.support.v4.widget.NestedScrollView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:layout_margin="10dp"
            android:background="#ffff"
            android:scrollbars="none"
            app:layout_behavior="@string/appbar_scrolling_view_behavior">

            <!-- your data will be scroll -->
            
        </android.support.v4.widget.NestedScrollView>

    </android.support.design.widget.CoordinatorLayout>
